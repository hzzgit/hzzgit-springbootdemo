package com.hzz.分区表创建小工具类;

import com.hzz.util.TimeUtils;

import java.util.Date;

public class 按月分区 {
    public static void main(String[] args) {
        String ptable="drivercardrecord";
        String sql="\t PARTITION BY RANGE ( TO_DAYS( operTime ) ) (";
        for (int i = 30; i >-30 ; i--) {
            String time = TimeUtils.getdatebyMonth2(new Date(),i);
            String yytime=TimeUtils.getdatebyMonth(new Date(),i-1);
            String name="\nPARTITION p_"+ptable+time+" " +
                    "\tVALUES\n" +
                    "\t\tLESS THAN ( TO_DAYS( '"+yytime+"-01' ) ) ENGINE = INNODB,";
            sql+=name;
        }
        sql = sql.substring(0, sql.length() - 1);
        sql+="  );";
        System.out.println(sql);

    }
}
