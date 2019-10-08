package com.hzz.service.moreinteface;

public class beanclase {

    private moreinteface moreinteface;

    public beanclase(com.hzz.service.moreinteface.moreinteface moreinteface) {
        this.moreinteface = moreinteface;
    }
    public void get(){
        moreinteface.moresay();
    }
}
