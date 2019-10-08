package com.hzz.util;

import java.util.HashMap;
import java.util.Map;

public enum  Cnmeiju {
    alarm("普通报警","212");

    private String name;
    private Object value;

    public String getName() {
        return name;
    }

    public Object getValue() {
        return value;
    }

    private static Map<Object, Cnmeiju> codeMap = null;

    static {
        codeMap=new HashMap<>();
        Cnmeiju[] values=  Cnmeiju.values();
        for (Cnmeiju value : values) {
            codeMap.put(value.value, value);
        }
    }

    private Cnmeiju(String name,Object val) {
        this.name=name;
        this.value=val;
    }


    //获取枚举名称
    public static String getName(Object val){
      String name="无";
        if(codeMap.containsKey(val)){
            name = codeMap.get(val).getName();
        }
        return name;
    }

    //是否存在该枚举名称
    public static  boolean existname(String name){
        Cnmeiju[] values=  Cnmeiju.values();
        boolean arg=false;
        for (Cnmeiju value : values) {
            if(name.equalsIgnoreCase(value.name())){
                arg=true;
                break;
            }
        }
        return  arg;
    }
    //是否存在该枚举值
    public static  boolean existval(Object val){
        boolean arg=false;
        if(codeMap.containsKey(val)){
            arg=true;
        }
        return  arg;
    }
}
