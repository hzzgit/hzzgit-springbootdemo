package com.hzz.异常处理;


import lombok.val;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RuntimeException {

    public static void main(String[] args) {

        try {

            new ArrayList<>().get(2);
        } catch (java.lang.RuntimeException e) {
            System.out.println("捕获异常:"+e);
        }

        List<String> data=new ArrayList<>();  //如果是运行异常可以不用进行捕获,其他异常如io等异常需要强制捕获异常
        String s = data.get(1);
        File file =new File("D://按月分区.txt");
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        val name="2121";

        System.out.println(name.length());
    }
}
