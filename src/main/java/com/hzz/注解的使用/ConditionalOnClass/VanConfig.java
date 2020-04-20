//package com.hzz.注解的使用.ConditionalOnClass;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
///**
// * @author hzz
// * @version 1.0
// * @date 2020/4/16 10:23
// */
//@Configuration
//@ConditionalOnClass({Billy.class})
//public class VanConfig {
//
//    @Bean
//    public Fighter billy(){
//        return new Billy();
//    }
//
//    @Bean
//    public Van van(Fighter fighter){
//        return new Van(fighter);
//    }
//}