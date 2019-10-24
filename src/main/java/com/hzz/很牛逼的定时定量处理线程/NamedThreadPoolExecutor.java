package com.hzz.很牛逼的定时定量处理线程;


import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NamedThreadPoolExecutor {
    private static Logger log = LoggerFactory.getLogger(NamedThreadPoolExecutor.class);

    public NamedThreadPoolExecutor() {
    }

    public static ThreadPoolExecutor newFixedThreadPool(int maxCnt, String preName) {
        return new ThreadPoolExecutor(maxCnt, maxCnt, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue(), new NamedThreadPoolExecutor.NamedThreadFactory(preName)) {
            protected void afterExecute(Runnable r, Throwable t) {
                super.afterExecute(r, (Throwable)t);
                if (t == null && r instanceof Future) {
                    try {
                        Object var3 = ((Future)r).get();
                    } catch (CancellationException var4) {
                        t = var4;
                    } catch (ExecutionException var5) {
                        t = var5.getCause();
                    } catch (InterruptedException var6) {
                        Thread.currentThread().interrupt();
                    }
                }

                if (t != null) {
                    NamedThreadPoolExecutor.log.error("ThreadPoolExecutor执行出错！preName=" + preName, (Throwable)t);
                }

            }
        };
    }

    public static ThreadFactory newThreadFactory(String preName) {
        return new NamedThreadPoolExecutor.NamedThreadFactory(preName);
    }

    public static ScheduledExecutorService newScheduledThreadPool(int corePoolSize, String preName) {
        return new ScheduledThreadPoolExecutor(corePoolSize, new NamedThreadPoolExecutor.NamedThreadFactory("Sche_" + preName));
    }

    static class NamedThreadFactory implements ThreadFactory {
        static final AtomicInteger POOL_NUMBER = new AtomicInteger(1);
        final ThreadGroup group;
        final AtomicInteger threadNumber = new AtomicInteger(1);
        final String namePrefix;

        NamedThreadFactory(String preName) {
            SecurityManager s = System.getSecurityManager();
            this.group = s != null ? s.getThreadGroup() : Thread.currentThread().getThreadGroup();
            this.namePrefix = preName + "-" + POOL_NUMBER.getAndIncrement() + "-thread-";
        }

        public Thread newThread(Runnable r) {
            Thread t = new Thread(this.group, r, this.namePrefix + this.threadNumber.getAndIncrement(), 0L);
            if (t.isDaemon()) {
                t.setDaemon(false);
            }

            if (t.getPriority() != 5) {
                t.setPriority(5);
            }

            return t;
        }
    }
}
