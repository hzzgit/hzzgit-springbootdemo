package com.hzz.很牛逼的定时定量处理线程;


import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class BlockedThreadPoolExecutor extends ThreadPoolExecutor {
    private ReentrantLock pauseLock;
    private Condition unpaused;
    private int size;
    private int currActive;

    public BlockedThreadPoolExecutor(int size) {
        this(size, "blockPool");
    }

    public BlockedThreadPoolExecutor(int size, String threadName) {
        super(size, size, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue(), new NamedThreadPoolExecutor.NamedThreadFactory(threadName));
        this.pauseLock = new ReentrantLock();
        this.unpaused = this.pauseLock.newCondition();
        this.currActive = 0;
        this.size = size;
    }

    public void changeBlockedThreadPoolSize(int size) {
        this.setMaximumPoolSize(size);
        this.setCorePoolSize(size);
        this.size = size;
    }

    public void execute(Runnable command) {
        this.pauseLock.lock();

        try {
            if (this.currActive >= this.size) {
                this.unpaused.await();
            }

            super.execute(command);
            ++this.currActive;
        } catch (InterruptedException var6) {
            throw new RuntimeException(var6);
        } finally {
            this.pauseLock.unlock();
        }

    }

    protected void afterExecute(Runnable r, Throwable t) {
        super.afterExecute(r, t);

        try {
            this.pauseLock.lock();
            --this.currActive;
            this.unpaused.signal();
        } finally {
            this.pauseLock.unlock();
        }

    }

    public static void main(String[] args) {
        BlockedThreadPoolExecutor blockedThreadPoolExecutor = new BlockedThreadPoolExecutor(3, "11");
        for (int i = 0; i <10; i++) {
            blockedThreadPoolExecutor.submit(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread());
                        try {
                            Thread.sleep(20000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                }
            });
        }
        //这边直接阻塞掉了
        while (blockedThreadPoolExecutor.getActiveCount()>1){
            try {
                System.out.println("当前活动中的线程"+blockedThreadPoolExecutor.getActiveCount());
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        
    }
}