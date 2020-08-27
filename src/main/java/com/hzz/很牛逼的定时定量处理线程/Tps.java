package com.hzz.很牛逼的定时定量处理线程;

//计算每分钟执行量

import java.util.Iterator;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class Tps {
    private ConcurrentHashMap<Long, AtomicInteger> minuteMap = new ConcurrentHashMap();

    public Tps() {
    }

    public void inc() {
        this.add(1);
    }

    public void add(int count) {
        long sec = System.currentTimeMillis() / 1000L;
        AtomicInteger counter = (AtomicInteger)this.minuteMap.get(sec);
        boolean checkMap = false;
        if (counter == null) {
            synchronized(this) {
                counter = (AtomicInteger)this.minuteMap.get(sec);
                if (counter == null) {
                    counter = new AtomicInteger(0);
                    this.minuteMap.put(sec, counter);
                    checkMap = true;
                }
            }
        }

        counter.getAndAdd(count);
        if (checkMap && this.minuteMap.size() > 300) {
            this.getTpm();
        }

    }

    public int getTps() {
        long sec = System.currentTimeMillis() / 1000L;
        AtomicInteger counter = (AtomicInteger)this.minuteMap.get(sec - 1L);
        return counter == null ? 0 : counter.get();
    }

    public int getTpm() {
        long sec = System.currentTimeMillis() / 1000L - 1L;
        long sec2 = sec - 60L;
        int count = 0;
        Iterator iter = this.minuteMap.entrySet().iterator();

        while(true) {
            while(iter.hasNext()) {
                Entry<Long, AtomicInteger> en = (Entry)iter.next();
                long t = (Long)en.getKey();
                if (t <= sec && t > sec2) {
                    count += ((AtomicInteger)en.getValue()).get();
                } else if (t <= sec2) {
                    iter.remove();
                }
            }

            return count;
        }
    }

    public static void main(String[] args) {
        Tps tps=new Tps();
        tps.add(1);
    }
}