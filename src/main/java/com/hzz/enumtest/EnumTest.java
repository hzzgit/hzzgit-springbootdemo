package com.hzz.enumtest;

public class EnumTest {
    Enumpeople nanren=Enumpeople.nanren;
    public static void main(String[] args) {
        String name=Color.RED.getName();
        System.out.println(Color.getName(1));

        System.out.println(AlarmType.getName(AlarmType.crossalarm.getAlarmType()));

    }
}
