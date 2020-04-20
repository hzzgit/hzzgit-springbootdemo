package com.hzz.设计模式.工厂来控制业务逻辑_spring加载顺序;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author hzz
 * @version 1.0
 * @date 2020/4/17 13:47
 */
@Slf4j
@Service
@Order(1)
public class PayFactory2 implements InitializingBean {
    private  Map<String, UserPayService> serviceMap = new ConcurrentHashMap<>();

    @Autowired
    private ApplicationContext context;

    public  UserPayService get(String name){
        UserPayService userPayService = serviceMap.get(name);
        return  userPayService;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Collection<UserPayService> values = context.getBeansOfType(UserPayService.class).values();
        for (UserPayService value : values) {
            String getname = value.getname();
            serviceMap.put(getname,value);
        }
        System.out.println("initebean初始化");
    }
}
