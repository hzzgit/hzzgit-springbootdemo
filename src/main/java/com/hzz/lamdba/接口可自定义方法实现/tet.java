package com.hzz.lamdba.接口可自定义方法实现;

import com.hzz.lamdba.GPSRealData;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * @author hzz
 * @version 1.0
 * @date 2020/4/16 14:00
 */
@Slf4j
public class tet {
    public static void main(String[] args) {
        NoReturnMultiParam noReturnMultiParam=(int a,int b )->{
            System.out.println(a+"--"+b);
        };
        noReturnMultiParam.method(1,2);
        ArrayList<Integer> list = new ArrayList<>();
        ArrayList<GPSRealData> gpsRealData = new ArrayList<>();
        Collections.addAll(list,1,2,111,4,5,6);
    //    list.sort(( a,b )-> b-a);
        Collections.sort(list,(a,b)->b-a);
        gpsRealData.sort((a,bv)->a.getSendTime().compareTo(bv.getSendTime()));
        for (Integer integer : list) {
            System.out.println(integer);
        }
        Consumer<String> consumer = ele -> {
            System.out.println(ele);
        };
        Function<String,Integer> function=fun ->{
            return Integer.valueOf(fun);
        };
        int a1=function.apply("1");

        consumer.accept("111");
    }
}
