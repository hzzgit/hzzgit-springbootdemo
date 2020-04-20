package com.hzz.注解的使用.ConditionalOnClass;

import lombok.extern.slf4j.Slf4j;

/**
 * @author hzz
 * @version 1.0
 * @date 2020/4/16 10:23
 */
@Slf4j
public class Banana implements Fighter {
    @Override
    public void fight() {
        System.out.println("banana:自由的气息，蕉迟但到");
    }
}
