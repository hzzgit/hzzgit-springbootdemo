package com.hzz.计算对象内存占用;

import com.alibaba.fastjson.JSON;
import com.carrotsearch.sizeof.RamUsageEstimator;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryPoolMXBean;
import java.lang.management.MemoryUsage;
import java.util.*;

public class memoryTest {
    public static void main(String[] args) {


     String sds="1";
      String l= RamUsageEstimator.humanSizeOf(sds);
        System.out.println(l);
        List<MemoryPoolMXBean> memoryPoolMXBeans = ManagementFactory.getMemoryPoolMXBeans();
        for (MemoryPoolMXBean mp : memoryPoolMXBeans) {
                MemoryUsage usage = mp.getUsage();
                Map old = new LinkedHashMap();
                old.put("max", usage.getMax() / 1024);
                old.put("used", usage.getUsed() / 1024);
                old.put("commited", usage.getCommitted() / 1024);
                old.put("init", usage.getInit() / 1024);
                System.out.println(mp.getName()+JSON.toJSONString(old));

        }
    }
}
