package com.hzz.很牛逼的定时定量处理线程;

import com.hzz.lamdba.Thead;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduTest {
    public static void main(String[] args) {
        ScheduledExecutorService tst = NamedThreadPoolExecutor.newScheduledThreadPool(2, "tst");
        tst.scheduleWithFixedDelay(()->{
            System.out.println(Thread.currentThread().getName());
            try {
                Thread.sleep(400000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(1);
        },1,3, TimeUnit.SECONDS);

        tst.scheduleWithFixedDelay(()->{
            System.out.println(Thread.currentThread().getName());
            try {
                Thread.sleep(400000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(1);
        },1,3, TimeUnit.SECONDS);

        tst.scheduleWithFixedDelay(()->{
            System.out.println(Thread.currentThread().getName());
            try {
                Thread.sleep(400000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(1);
        },1,3, TimeUnit.SECONDS);
    }
}
