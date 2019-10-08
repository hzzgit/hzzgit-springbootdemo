package com.hzz.service.moreinteface;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("moreService2")
public class moreService2 implements  moreinteface {
    @Override
    public void moresay() {
        System.out.println(2);
    }
}
