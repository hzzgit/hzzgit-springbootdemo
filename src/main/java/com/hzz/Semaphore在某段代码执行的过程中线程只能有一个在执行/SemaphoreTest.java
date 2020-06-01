package com.hzz.Semaphore在某段代码执行的过程中线程只能有一个在执行;

import java.util.concurrent.Semaphore;

public class SemaphoreTest {
    private Semaphore s=new Semaphore(2);//这个是一个线程并发的对象,如果使用了这个对象,那么就可以让这个对象两个方法之间对线程处理的内容进行最大线程并发量的限制

    public void test(){
        try {
            s.acquire();
            System.out.println(Thread.currentThread().getName());
            Thread.sleep(5000);
            s.release();

            s.acquire();
            System.out.println(Thread.currentThread().getName()+"第二次线程阻隔");
            Thread.sleep(2000);
            s.release();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
