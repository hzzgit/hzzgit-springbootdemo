package com.hzz.枚举;

public enum AlarmTypeEnum {
    //1路报警类型
    TEMPERATURE1("HighTemperature1", "高温一路报警", "LowTemperature1", "低温一路报警"),
    //2路报警类型
    TEMPERATURE2("HighTemperature2", "高温二路报警", "LowTemperature2", "低温二路报警"),
    //3路报警类型
    TEMPERATURE3("HighTemperature3", "高温三路报警", "LowTemperature3", "低温三路报警"),

    //4路报警类型
    TEMPERATURE4("HighTemperature4", "高温四路报警", "LowTemperature4", "低温四路报警");


    AlarmTypeEnum(String highalarmtype, String highalarmname, String lowalarmtype, String lowalarmname) {
        this.highalarmtype = highalarmtype;
        this.highalarmname = highalarmname;
        this.lowalarmtype = lowalarmtype;
        this.lowalarmname = lowalarmname;
    }

    private String highalarmtype;
    private String highalarmname;

    private String lowalarmtype;
    private String lowalarmname;

    public String getHighalarmtype() {
        return highalarmtype;
    }

    public void setHighalarmtype(String highalarmtype) {
        this.highalarmtype = highalarmtype;
    }

    public String getHighalarmname() {
        return highalarmname;
    }

    public void setHighalarmname(String highalarmname) {
        this.highalarmname = highalarmname;
    }

    public String getLowalarmtype() {
        return lowalarmtype;
    }

    public void setLowalarmtype(String lowalarmtype) {
        this.lowalarmtype = lowalarmtype;
    }

    public String getLowalarmname() {
        return lowalarmname;
    }

    public void setLowalarmname(String lowalarmname) {
        this.lowalarmname = lowalarmname;
    }
}
