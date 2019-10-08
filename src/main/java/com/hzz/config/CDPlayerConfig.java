package com.hzz.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(CDConfig.class)  //导入CDConfig的配置
public class CDPlayerConfig {
    @Bean(name = "cDPlayer")
    public CDPlayer cdPlayer(CompactDisc heyGirl) {
    /* 这里注入的bean是
    上面CDConfig.java中的name为heyGirl的CompactDisc类型的bean*/
        return new CDPlayer(heyGirl);
    }

}