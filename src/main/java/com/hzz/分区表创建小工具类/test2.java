package com.hzz.分区表创建小工具类;

import java.util.Date;

public class test2 {
    public static void main(String[] args) {

        System.out.println(new Date().compareTo( new Date()));
        boolean ispika = new Date().compareTo( new Date()) > -1 ? true : false;
        System.out.println(ispika);

    }
}
