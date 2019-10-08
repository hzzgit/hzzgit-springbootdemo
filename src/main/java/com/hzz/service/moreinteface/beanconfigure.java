package com.hzz.service.moreinteface;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class beanconfigure {

    @Autowired
    @Qualifier("moreService1")
    private moreinteface moreinteface;

    @Bean
    public beanclase getbean( ){
        return  new beanclase(moreinteface);
    }
}
