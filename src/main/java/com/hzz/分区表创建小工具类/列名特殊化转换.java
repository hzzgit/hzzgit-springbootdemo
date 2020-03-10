package com.hzz.分区表创建小工具类;

public class 列名特殊化转换 {
    public static void main(String[] args) {
        String datas="";
        for (int i = 1; i <49 ; i++) {
            datas+="type"+i+"cn type"+i+"num,";
        }
        System.out.println(datas);
    }
}
