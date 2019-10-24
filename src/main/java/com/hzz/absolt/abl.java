package com.hzz.absolt;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class abl extends  abclase {

   // @PostConstruct
    public void init1(){
        init("第一个");
    }

    @Override
    public boolean acceptData(String key) {
        System.out.println(key);
        return false;
    }
}
