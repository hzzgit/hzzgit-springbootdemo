package com.hzz.设计模式.工厂来控制业务逻辑_spring加载顺序;

import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author hzz
 * @version 1.0
 * @date 2020/4/17 13:47
 */
@Slf4j
public class PayFactory {
    private static Map<String, UserPayService> serviceMap = new ConcurrentHashMap<>();


    public static UserPayService get(String name){
        UserPayService userPayService = serviceMap.get(name);
        return  userPayService;
    }

    public static void register(String name  ,UserPayService userPayService) {
            serviceMap.put(name,userPayService);
    }

}
