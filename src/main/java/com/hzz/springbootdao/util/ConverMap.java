package com.hzz.springbootdao.util;

import java.util.*;

/**
 * 类型自动转换的Map类
 */
public class ConverMap extends HashMap implements Map {

    public String getString(String key,String defaultvalue){
        return  ConverterUtils.toString(get(key),defaultvalue);
    }

    public Integer getInt(String key,Integer defaultvalue){
        return  ConverterUtils.toInt(get(key),defaultvalue);
    }

    public Long getLong(String key,Long defaultvalue){
        return  ConverterUtils.toLong(get(key),defaultvalue);
    }

    public Double getDouble(String key,Double defaultvalue){
        return  ConverterUtils.toDouble(get(key),defaultvalue);
    }

    public Float getFloat(String key,Float defaultvalue){
        return  ConverterUtils.toFloat(get(key),defaultvalue);
    }

    public Date getDate(String key, Date defaultvalue){
        return  ConverterUtils.toDate(get(key),defaultvalue);
    }



}
