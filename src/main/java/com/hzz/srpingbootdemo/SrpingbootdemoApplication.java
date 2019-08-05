package com.hzz.srpingbootdemo;

import com.github.pagehelper.PageHelper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.Properties;

@SpringBootApplication
@EnableScheduling
@MapperScan("com.hzz.dao")
@ComponentScan(basePackages = {"com.hzz"})
public class SrpingbootdemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SrpingbootdemoApplication.class, args);
    }


}
