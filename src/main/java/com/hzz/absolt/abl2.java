package com.hzz.absolt;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class abl2 extends abclase {

    @PostConstruct
    public void init1(){
      isinterface isinterface=new isinterfaceclass() ;
        isinterface.init();
        System.out.println(1);
    }

    @Override
    public boolean acceptData(String key) {
        System.out.println(key);
        return false;
    }
}
