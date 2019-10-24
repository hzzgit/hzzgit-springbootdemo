package com.hzz.thread;

import com.hzz.lamdba.Student;

import java.util.Map;
import java.util.TreeMap;

public class SynchronizedClassClass5 implements Runnable {
    static  SynchronizedClassClass5 instance1=new SynchronizedClassClass5();
    static SynchronizedClassClass5 instance2=new SynchronizedClassClass5();
    @Override
    public void run() {
        method();
    }

    public static void main(String[] args) {
        TreeMap treeMap = new TreeMap();
        treeMap.put(1, 33);
        treeMap.put(12, 33);
        treeMap.put(4, 33);
        treeMap.put(3, 33);
        treeMap.put(2, 33);
        treeMap.put(1212, 33);
        treeMap.replace(1212, 4523);
        System.out.println(treeMap);
        treeMap.forEach((p,v)->{
            System.out.println(p);
        });
        Map.Entry entry = treeMap.firstEntry();
        entry.getKey();
        entry.getValue();

        Thread t1=new Thread(instance1);
        Thread t2=new Thread(instance2);
        t1.start();
        t2.start();
        while (t1.isAlive()||t2.isAlive())
        {

        }
        System.out.println("结束");
    }

    private  void method() {
        synchronized (Student.class) {
            System.out.println(Thread.currentThread().getName() + "lock1");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "运行结束1");
        }
    }
}