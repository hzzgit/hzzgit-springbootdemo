package com.hzz.lamdba;

import com.hzz.springbootdao.util.ConverMap;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ForEach {

    public static <T, R> R excute(T t, Function<T, R> fun) {
        return fun.apply(t);
    }


    public static <T> List<T> excute2(T t, Consumer<T> fun) {
        List<T> list = new ArrayList<>();
        fun.accept(t);
        list.add(t);
        return list;
    }


    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(11, 222, 31, 422, 51);
        List<ConverMap> datas = new ArrayList<>();
        ConverMap converMap = new ConverMap();
        converMap.put("name", "12");
        ConverMap converMap1 = new ConverMap();
        converMap1.put("name", "1234");
        ConverMap converMap2 = new ConverMap();
        converMap2.put("name", "112");
        ConverMap converMap3 = new ConverMap();
        converMap3.put("name", "2122");
        ConverMap converMap4 = new ConverMap();
        converMap4.put("name", "412");
        datas.add(converMap);
        datas.add(converMap1);
        datas.add(converMap2);
        datas.add(converMap3);
        datas.add(converMap4);
        datas.sort((co1,co2)->{
             return   co1.getInt("name").compareTo( co2.getInt("name"));
        });
        Map<String,ConverMap> da=datas.stream().collect(Collectors.toMap(p->p.getString("name"),v->v,(p,v)->v));
        System.out.println(datas);
        Collection<ConverMap> datas1= new ArrayList<>();
        datas1=  da.values();
        for (ConverMap o : datas1) {

        }
        System.out.println(datas1);
        Collections.sort(numbers, Comparator.comparing(Integer::intValue));
        System.out.println(numbers);
        numbers.sort((p1, p2) -> p1.compareTo(p2));
        List<ConverMap> lists = new ArrayList<>();
        lists = datas.stream().sorted(Comparator.comparing(p->p.getInt("name"))).collect(Collectors.toList());
        datas.stream().collect(Collectors.toList());
        System.out.println(lists==datas);
        System.out.println(1);
//        numbers.forEach(p->{
//            if(p<5){
//                System.out.println(p);
//                List te=excute(p,fun->{
//                    List<String> te1=new ArrayList<>();
//                    for (int i = 0; i <p ; i++) {
//                        te1.add(String.valueOf(i));
//                    }
//                    return te1;
//                });
//                lists.add(te);
//            }
//        });
        Map<String, Integer> te = new HashMap<>();
        List<Map<String, Integer>> name = excute2(te, p -> p.put("dasdas", 2312));
        System.out.println(name);
    }
}
