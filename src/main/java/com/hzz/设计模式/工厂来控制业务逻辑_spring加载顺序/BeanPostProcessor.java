package com.hzz.设计模式.工厂来控制业务逻辑_spring加载顺序;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * @author hzz
 * @version 1.0
 * @date 2020/4/17 14:09
 */
@Slf4j
@Service
public class BeanPostProcessor implements org.springframework.beans.factory.config.BeanPostProcessor {

    @PostConstruct
    public void init(){
        System.out.println("最早的");
    }


    public static void main(String[] args) {
        short speed=1;
        System.out.println(speed / 10.0);
    }
}
