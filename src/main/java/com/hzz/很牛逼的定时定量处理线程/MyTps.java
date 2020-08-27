package com.hzz.很牛逼的定时定量处理线程;

import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2020/8/27 16:24
 */
public class MyTps {

    private ConcurrentHashMap <Long,AtomicInteger> data=new ConcurrentHashMap<>();

    /**
     * 添加tps和tpm计算基础
     * @param co
     */
    public void add(Integer co){
        long l = System.currentTimeMillis()/1000L;//当前毫秒值
        AtomicInteger atomicInteger = data.get(l);
        if(atomicInteger==null){
            synchronized (this){
                atomicInteger=data.get(l);
                if(atomicInteger==null){
                    atomicInteger=new AtomicInteger(0);
                    data.put(l,atomicInteger);
                }
            }
        }
        atomicInteger.getAndAdd(co);
        if(data.size()>300){
            gettpm();
        }
    }

    /**
     * 每秒钟访问量
     * @return
     */
    public int gettps(){
        long l = System.currentTimeMillis()/1000L;//当前毫秒值
        AtomicInteger atomicInteger = data.get(l - 1);//上一秒的值
        if(atomicInteger!=null){
          return  atomicInteger.get();
        }else{
           return  0;
        }
    }

    /**
     * 每分钟访问量
     * @return
     */
    public int gettpm(){
        long l = System.currentTimeMillis()/1000L;//当前毫秒值
        AtomicInteger atomicInteger=new AtomicInteger(0);
    
        Iterator iterator=data.entrySet().iterator();
        while (iterator.hasNext())
        {
            Map.Entry<Long,AtomicInteger> next = (Map.Entry) iterator.next();
            Long key = next.getKey();
            if(l-key<=60){
                atomicInteger.addAndGet(next.getValue().get());
            }
           else  if(l-key>120){
                data.remove(key);
            }

        }
        return atomicInteger.get();
    }

    public static void main(String[] args) throws InterruptedException {

        MyTps myTps=new MyTps();

        for (int i = 0; i <100 ; i++) {
                 new Thread(()->{
                                 while (true){
                                     myTps.add(new Random().nextInt(5));
                                     try {
                                         Thread.sleep(100);
                                     } catch (InterruptedException e) {
                                         e.printStackTrace();
                                     }
                                 }
                         }).start();
        }

             new Thread(()->{
                             while (true){
                                 try {
                                     int gettps = myTps.gettps();
                                     System.out.println("秒："+ gettps);
                                     System.out.println("分："+myTps.gettpm());
                                     Thread.sleep(1000);
                                 } catch (InterruptedException e) {
                                     e.printStackTrace();
                                 }
                             }
                     }).start();
    }
}
