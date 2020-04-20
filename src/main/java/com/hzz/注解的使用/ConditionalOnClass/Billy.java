package com.hzz.注解的使用.ConditionalOnClass;

import lombok.extern.slf4j.Slf4j;

/**
 * @author hzz
 * @version 1.0
 * @date 2020/4/16 10:23
 */
@Slf4j
public class Billy implements Fighter{

    @Override
    public void fight(){
        System.out.println("billy：吾乃新日暮里的王，三界哲学的主宰。");
    }
}