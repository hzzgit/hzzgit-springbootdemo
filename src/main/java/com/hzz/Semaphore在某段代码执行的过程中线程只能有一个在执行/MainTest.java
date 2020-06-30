package com.hzz.Semaphore在某段代码执行的过程中线程只能有一个在执行;

public class MainTest {
    public static void main(String[] args) {
        SemaphoreTest semaphoreTest = new SemaphoreTest();
        for (int i = 0; i < 10; i++) {
                     new Thread(()->{
                         semaphoreTest.test();
                             }).start();
        }
//
//
//        for (int i = 0; i <10 ; i++) {
//            new Thread(()->{
//                System.out.println(Thread.currentThread().getName()+"独立线程");
//            }).start();
//        }

//        ExecutorService executorService = Executors.newFixedThreadPool(1);
//        for (int i = 0; i < 10; i++) {
//            executorService.submit(new Runnable() {
//                @Override
//                public void run() {
//                    while (true){
//                        try {
//                            System.out.println(Thread.currentThread().getName()+"这边是线程10个池的情况");
//                            Thread.sleep(1000);
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }
//            });
//        }
//
//        try {
//            Thread.sleep(20000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        System.out.println("调整成5个线程池");
//        executorService=Executors.newFixedThreadPool(5);
        System.out.println(Runtime.getRuntime().availableProcessors()*2+2+"这个数值是处理器可用的数量");

    }
}
