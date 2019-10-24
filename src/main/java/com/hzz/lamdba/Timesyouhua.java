package com.hzz.lamdba;

public class Timesyouhua {
    public static void main(String[] args) {

        long s=System.currentTimeMillis();   //获取开始时间
        Integer a = new Integer(20);
        String name="";
        StringBuffer sfsa = new StringBuffer(a.toString());
        sfsa.append("3213");
        sfsa.append("3213");
        sfsa.append("3213");
        sfsa.append("3213");
        for (Integer i = 0; i < 2000000; i++) {
            sfsa.append(i.toString());
        }

        long e=System.currentTimeMillis(); //获取结束时间

        try {
            Thead.Apple apple = null;
            apple.getWeight();
        } catch (RuntimeException e1) {
            System.out.println(e1);
        } catch (Exception e1) {
            System.out.println(e1);
        }
        System.out.println("保险报表多车查询运行时间："+(e-s)+"ms");

    }
}
