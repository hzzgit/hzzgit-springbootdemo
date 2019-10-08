package com.hzz.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration  // @Configuration注解表示定义一个配置类
public class CDConfig {
    // 方法名heyGirl即是bean的name
    @Bean   // 将SgtPeppers注册为 SpringContext中的bean
    public CompactDisc heyGirl() {
        return new SgtPeppers();  // CompactDisc类型的
    }
}