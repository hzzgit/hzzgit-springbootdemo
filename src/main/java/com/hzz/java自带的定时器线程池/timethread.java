package com.hzz.java自带的定时器线程池;

import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class timethread {
    public static void main(String[] args) {
        ScheduledExecutorService scheduledExecutorService=  Executors.newSingleThreadScheduledExecutor();

        /**
         *  每两秒统计一下连接数
         */
        scheduledExecutorService.scheduleAtFixedRate(() -> {
            System.out.println("connections: " + 2);
            try {
                new ArrayList<>().get(1);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, 0, 2, TimeUnit.SECONDS);

    }
}
