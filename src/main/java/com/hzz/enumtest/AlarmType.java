package com.hzz.enumtest;

public enum  AlarmType {
    crossalarm("出围栏报警", "crossalarm"), higealarm("高速报警", "higealarm");

    private String name;
    private String alarmType;

     AlarmType(String name, String alarmType) {
        this.name = name;
        this.alarmType = alarmType;
    }
    // 普通方法
    public static String getName(String alarmType) {
        for (AlarmType c : AlarmType.values()) {
            if(c.alarmType.equalsIgnoreCase(alarmType)){
                return  c.name;
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlarmType() {
        return alarmType;
    }

    public void setAlarmType(String alarmType) {
        this.alarmType = alarmType;
    }
}
