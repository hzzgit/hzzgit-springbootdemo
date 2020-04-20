package com.hzz.注解的使用.builder创建bean;

import lombok.Builder;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

/**
 * @author hzz
 * @version 1.0
 * @date 2020/4/17 14:52
 */
@Slf4j
@Builder
public class BuilderBean {

    private String name;
    private String age;
    private Date time ;

    public static void main(String[] args) {
        BuilderBean build = BuilderBean.builder().name("1").build();

    }
}
