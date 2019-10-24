package com.hzz.lamdba;

import com.ltmonitor.util.StringUtil;
import net.fxft.cloud.mvc.SignUtil;
import net.fxft.common.jdbc.RowDataMap;

import java.util.*;
import java.util.stream.Collectors;

public class Thead {
    public static class Apple {
        private String color;
        private Integer weight;
        private Integer longs;

        public Apple(String color, Integer weight,Integer longs) {
            this.color = color;
            this.weight = weight;
            this.longs=longs;
        }

        public Integer getLongs() {
            return longs;
        }

        public void setLongs(Integer longs) {
            this.longs = longs;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public Integer getWeight() {
            return weight;
        }

        public void setWeight(Integer weight) {
            this.weight = weight;
        }

        @Override
        public String toString() {
            return "Apple{" +
                    "color='" + color + '\'' +
                    ", weight=" + weight +
                    '}';
        }
    }
    public interface Predicate<T> {
        boolean test(T t);
    }

    public static <T> List<T> filter(List<T> list, Predicate<T> p) {
        List<T> result = new ArrayList<>();
        for (T e : list) {
            if (p.test(e)) {
                result.add(e);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        List<Apple> apples = new ArrayList<Apple>();
        Apple apple1 = new Apple("red", 100,12);
        Apple apple2 = new Apple("green", 200,2222);
        Apple apple5 = new Apple("green", 200,343);
        Apple apple6 = new Apple("green", 200,1343);
        Apple apple3 = new Apple("red", 300,1);
        Apple apple4 = new Apple("red", 150,3);
        apples.add(apple1);
        apples.add(apple2);
        apples.add(apple3);
        apples.add(apple4);
        apples.add(apple5);
        apples.add(apple6);
        apples.sort(Comparator.comparing(Apple::getWeight).thenComparing(Apple::getLongs).reversed());
        Map<Integer, List<Apple>> integerListMap = apples.stream().collect(Collectors.groupingBy(Apple::getWeight));
        Map<Integer, Apple> integerListMa2p = apples.stream().collect(Collectors.toMap(Apple::getWeight,v->v,(k,v)->v));
        //Map<Integer, String> wecolor = apples.stream().collect(Collectors.toMap(p -> p.getWeight(), v -> v.getColor(), (olddata, newdata) -> olddata));
        Map<Integer, String> wecolor = apples.stream().collect(Collectors.toMap(p -> p.getWeight(), v -> "key"+v.getColor(), (olddata, newdata) -> olddata));

        System.out.println(integerListMap);
        System.out.println(apples);
        List<Apple> result = filter(apples, (Apple apple) -> "red".equals(apple.getColor())||apple.getWeight()<=200);
        System.out.println(result);
    }
}
