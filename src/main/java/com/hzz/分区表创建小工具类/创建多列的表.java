package com.hzz.分区表创建小工具类;

import com.ltmonitor.util.StringUtil;

public class 创建多列的表 {
    public static void main(String[] args) {
        String names="1/常跑路线运营次数占比,2/平均运距,3/运营率,4/日均行驶里程,5/日均运营时长,6/日均夜间运营时长,7/日均黄昏运营时长,8/日均中午运营时长,9/日均清晨运营时长,10/夜间行驶里程占比,11/平均车速,12/日均疲劳驾驶时长,13/日均严重疲劳驾驶时长,14/疲劳驾驶时长占比,15/严重疲劳驾驶时长占比,16/超速行驶里程占比,17/严重超速行驶里程占比,18/严重超速行驶时长占比";
        names = names.trim();
        String nas[]=names.split(",");
        String datas="";
        for (String na : nas) {
            if (!StringUtil.isNullOrEmpty(na)) {
                String ns[]=na.split("/");
                String na1 = ns[0];
                String com = ns[1];
                String d1="`type"+na1.trim()+"num`  VARCHAR (255) DEFAULT NULL COMMENT '"+com.trim()+"' ,";
                datas+=d1;
            }
        }
        System.out.println(datas);
    }

//
//    public static void main(String[] args) {
//        String datas="";
//        for (int i = 1; i <49 ; i++) {
//                String d1="`type"+i+"num`  varchar(255) DEFAULT NULL COMMENT '见logisticsstatistics表 type从第四个开始代表这边的第一个',";
//                datas+=d1;
//
//        }
//        System.out.println(datas);
//    }
}
