package com.hzz.设计模式.工厂来控制业务逻辑_spring加载顺序;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

/**
 * @author hzz
 * @version 1.0
 * @date 2020/4/17 13:49
 */
@Slf4j
@Service
@Order(2)
public class PayUserServiceimp implements  UserPayService , InitializingBean {

    @Override
    public void pay() {
        System.out.println("购买了这个用户的");
    }


    //这个可以让类在初始化之后执行这个方法
    @Override
    public void afterPropertiesSet() throws Exception {
            PayFactory.register("test",this);
        System.out.println("initebean初始化工厂组成类");

    }

    public String getname(){
        return  "test";
    }
}
