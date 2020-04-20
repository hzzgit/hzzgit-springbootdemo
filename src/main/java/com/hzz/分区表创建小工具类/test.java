package com.hzz.分区表创建小工具类;

import com.hzz.util.TimeUtils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Date;

public class test {
    public static void main(String[] args) {
        String ptable="logisgpsrealdata";
        String sql="\t PARTITION BY RANGE ( TO_DAYS( createDate ) ) (";
        for (int i = 0; i >-30 ; i--) {
            String time = TimeUtils.getdatebyMonth2(new Date(),i);
            String yytime=TimeUtils.getdatebyMonth(new Date(),i-1);
            String name="\nPARTITION "+ptable+time+" " +
                    "\tVALUES\n" +
                    "\t\tLESS THAN ( TO_DAYS( '"+yytime+"-01' ) ) ENGINE = INNODB,";
            sql+=name;
        }
        sql = sql.substring(0, sql.length() - 1);
        sql+="  );";
        System.out.println(sql);
        BigInteger bigInteger=new BigInteger("12111111111111111111112111111111111111111111111111111111111111111111111111111111111111111111111111");
        System.out.println(bigInteger);
    }
}
