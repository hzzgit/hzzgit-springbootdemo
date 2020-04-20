package com.hzz.设计模式.工厂来控制业务逻辑_spring加载顺序;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

/**
 * @author hzz
 * @version 1.0
 * @date 2020/4/17 13:53
 */
@Slf4j
@Service
@Order(value = 2) //指定先后加载的顺序
public class test implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        UserPayService test = PayFactory.get("test");
        test.pay();
    }
}
