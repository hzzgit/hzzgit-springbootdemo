package com.hzz.注解的使用.Import;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author hzz
 * @version 1.0
 * @date 2020/4/16 11:32
 */
@Slf4j
@Component
@Import({Cat.class,Dog.class})
public class testImportClass  {

    @Autowired
    private ApplicationContext applicationContext;
    @PostConstruct
    public void test(){
        Cat bean = applicationContext.getBean(Cat.class);
        bean.up();
    }


}
