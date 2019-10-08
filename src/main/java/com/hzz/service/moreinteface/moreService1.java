package com.hzz.service.moreinteface;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("moreService1")
public class moreService1 implements  moreinteface {
    @Override
    public void moresay() {
        System.out.println(1);
    }
}
