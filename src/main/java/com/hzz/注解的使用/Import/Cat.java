package com.hzz.注解的使用.Import;

import lombok.extern.slf4j.Slf4j;

/**
 * @author hzz
 * @version 1.0
 * @date 2020/4/16 11:32
 */
@Slf4j
public class Cat {
    private String name="import注解";
    public void up(){
        System.out.println(name);
    }
}
