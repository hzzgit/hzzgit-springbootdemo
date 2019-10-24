package com.hzz.很牛逼的定时定量处理线程;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractBatchExecThreadPoolExecutor<K, T> {
    private static Logger log = LoggerFactory.getLogger(AbstractBatchExecThreadPoolExecutor.class);
    private int alarmThreadRunTime;
    private String fLogName;
    private int batchMaxCnt;
    private int eachQueueConcurrent;
    private Map<K, AbstractBatchExecThreadPoolExecutor<K, T>.SerialQueue> serialMap;
    private ScheduledExecutorService ses;
    private ThreadPoolExecutor runEc;
    private Map<String, Long> threadMonitorMap;

    public AbstractBatchExecThreadPoolExecutor(String logName, int poolSize) {
        this(logName, poolSize, 2000, 2, 300);
    }

    public AbstractBatchExecThreadPoolExecutor(String logName, int poolSize, int batchMaxCnt, int eachQueueConcurrent, int checkTimeMill) {
        this(logName, batchMaxCnt, eachQueueConcurrent, checkTimeMill, NamedThreadPoolExecutor.newFixedThreadPool(poolSize, logName));
    }

    public AbstractBatchExecThreadPoolExecutor(String logName, int batchMaxCnt, int eachQueueConcurrent, int checkMillTime, ThreadPoolExecutor runEc) {
        this.alarmThreadRunTime = 30000;
        this.serialMap = new ConcurrentHashMap();
        this.ses = null;
        this.runEc = null;
        this.threadMonitorMap = new ConcurrentHashMap();
        this.runEc = runEc;
        this.fLogName = logName;
        this.batchMaxCnt = batchMaxCnt;
        this.eachQueueConcurrent = eachQueueConcurrent;
        this.ses = NamedThreadPoolExecutor.newScheduledThreadPool(2, logName);
        this.ses.scheduleWithFixedDelay(new Runnable() {
            public void run() {
                try {
                    Iterator iter = AbstractBatchExecThreadPoolExecutor.this.serialMap.keySet().iterator();

                    while(iter.hasNext()) {
                        K key = (K) iter.next();
                        AbstractBatchExecThreadPoolExecutor.this.submitNext(key);
                    }
                } catch (Throwable var3) {
                    AbstractBatchExecThreadPoolExecutor.log.error("[" + AbstractBatchExecThreadPoolExecutor.this.fLogName + "]BatchExecThreadPoolExecutor定时提交submitNext出错！", var3);
                }

            }
        }, (long)checkMillTime, (long)checkMillTime, TimeUnit.MILLISECONDS);
        this.ses.scheduleWithFixedDelay(new Runnable() {
            public void run() {
                try {
                    long deltimeout = 43200000L;
                    int resetExecCntTimeout = '\uea60';
                    Iterator iter = AbstractBatchExecThreadPoolExecutor.this.serialMap.entrySet().iterator();

                    while(iter.hasNext()) {
                        Entry<K, AbstractBatchExecThreadPoolExecutor<K, T>.SerialQueue> en = (Entry)iter.next();
                        AbstractBatchExecThreadPoolExecutor<K, T>.SerialQueue sq = (AbstractBatchExecThreadPoolExecutor.SerialQueue)en.getValue();
                        if (sq.queue.isEmpty()) {
                            K key = en.getKey();
                            if (System.currentTimeMillis() - sq.lastExecTime > deltimeout) {
                                AbstractBatchExecThreadPoolExecutor.log.info("[" + AbstractBatchExecThreadPoolExecutor.this.fLogName + "]BatchExecThreadPoolExecutor中Key=" + key + "的待发队列长期没用，被删除！");
                                iter.remove();
                            }
                        }

                        if (System.currentTimeMillis() - sq.lastExecTime > (long)resetExecCntTimeout) {
                        }
                    }
                } catch (Throwable var8) {
                    AbstractBatchExecThreadPoolExecutor.log.error("[" + AbstractBatchExecThreadPoolExecutor.this.fLogName + "]BatchExecThreadPoolExecutor清除没用的SerialQueue出错！", var8);
                }

            }
        }, 30L, 30L, TimeUnit.MINUTES);
    }

    public boolean isAllFinished() {
        if (this.runEc == null) {
            return true;
        } else {
            Iterator var1 = this.serialMap.values().iterator();

            while(var1.hasNext()) {
                AbstractBatchExecThreadPoolExecutor<K, T>.SerialQueue sq = (AbstractBatchExecThreadPoolExecutor.SerialQueue)var1.next();
                if (sq.queue.size() != 0) {
                    return false;
                }
            }

            return this.runEc.getQueue().size() == 0 && this.runEc.getActiveCount() == 0;
        }
    }

    public void shutdown() {
        if (this.runEc != null) {
            this.runEc.shutdown();
        }

        if (this.ses != null) {
            this.ses.shutdown();
        }

    }

    public ThreadPoolExecutor getThreadPoolExecutor() {
        return this.runEc;
    }

    public abstract K getQueueSplitKey(T req);

    public abstract void batchRun(K splitKey, List<T> reqlist);

    public void submit(T req) {
        AbstractBatchExecThreadPoolExecutor<K, T>.SerialQueue queue = null;
        K splitkey = this.getQueueSplitKey(req);
        synchronized(this.serialMap) {
            queue = (AbstractBatchExecThreadPoolExecutor.SerialQueue)this.serialMap.get(splitkey);
            if (queue == null) {
                queue = new AbstractBatchExecThreadPoolExecutor.SerialQueue();
                this.serialMap.put(splitkey, queue);
            }
        }

        queue.queue.offer(req);
    }

    private void minusExecCnt(K splitkey) {
        AbstractBatchExecThreadPoolExecutor<K, T>.SerialQueue sq = (AbstractBatchExecThreadPoolExecutor.SerialQueue)this.serialMap.get(splitkey);
        if (sq != null) {
            sq.execCnt.getAndDecrement();
        }

    }

    private boolean submitNext(K splitkey) {
        boolean reb = false;
        AbstractBatchExecThreadPoolExecutor<K, T>.SerialQueue sq = (AbstractBatchExecThreadPoolExecutor.SerialQueue)this.serialMap.get(splitkey);
        if (sq != null && !sq.queue.isEmpty()) {
            while(sq.execCnt.get() < this.eachQueueConcurrent) {
                sq.execCnt.getAndIncrement();
                ArrayList reqlist = new ArrayList();

                while(reqlist.size() < this.batchMaxCnt) {
                    T ssdreq = sq.queue.poll();
                    if (ssdreq == null) {
                        break;
                    }

                    reqlist.add(ssdreq);
                }

                int submitSize = reqlist.size();
                if (submitSize > 0) {
                    sq.lastExecTime = System.currentTimeMillis();
                    this.runEc.submit(new AbstractBatchExecThreadPoolExecutor.BatchExecThreadPoolRunnable(splitkey, reqlist));
                    reb = true;
                } else {
                    sq.execCnt.getAndDecrement();
                }

                if (submitSize < this.batchMaxCnt) {
                    break;
                }
            }
        }

        return reb;
    }

    public int getAllQueueSize() {
        int cnt = 0;

        Entry en;
        for(Iterator var2 = this.serialMap.entrySet().iterator(); var2.hasNext(); cnt += ((AbstractBatchExecThreadPoolExecutor.SerialQueue)en.getValue()).queue.size()) {
            en = (Entry)var2.next();
        }

        return cnt;
    }

    public String getLogName() {
        return this.fLogName;
    }

    public int getBatchMaxCnt() {
        return this.batchMaxCnt;
    }

    class BatchExecThreadPoolRunnable implements Runnable {
        private List<T> reqlist;
        private K splitKey;

        public BatchExecThreadPoolRunnable(K splitKey, List<T> reqlist) {
            this.splitKey = splitKey;
            this.reqlist = reqlist;
        }

        public void run() {
            String tname = Thread.currentThread().getName();
            AbstractBatchExecThreadPoolExecutor.this.threadMonitorMap.put(tname, System.currentTimeMillis());

            try {
                AbstractBatchExecThreadPoolExecutor.this.batchRun(this.splitKey, this.reqlist);
            } catch (Throwable var11) {
                AbstractBatchExecThreadPoolExecutor.log.error("[" + AbstractBatchExecThreadPoolExecutor.this.fLogName + "]BatchSubmitSmsToDbRunnable.batchRun出错！splitKey=" + this.splitKey, var11);
            } finally {
                try {
                    AbstractBatchExecThreadPoolExecutor.this.minusExecCnt(this.splitKey);
                } catch (Exception var10) {
                    AbstractBatchExecThreadPoolExecutor.log.error("[" + AbstractBatchExecThreadPoolExecutor.this.fLogName + "]BatchExecThreadPoolRunnable.submitNext出错!splitKey=" + this.splitKey, var10);
                }

                AbstractBatchExecThreadPoolExecutor.this.threadMonitorMap.remove(tname);
            }

        }
    }

    class SerialQueue {
        long lastExecTime = System.currentTimeMillis();
        LinkedBlockingQueue<T> queue = new LinkedBlockingQueue();
        AtomicInteger execCnt = new AtomicInteger(0);

        SerialQueue() {
        }
    }
}
