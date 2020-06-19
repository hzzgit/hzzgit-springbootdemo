package com.hzz.分区表创建小工具类;

import com.hzz.util.TimeUtils;

public class 按天分区 {
    public static void main(String[] args) {
        String ptable="gpstemperature";
        String sql="\t PARTITION BY RANGE ( TO_DAYS( sendTime ) ) (";
        for (int i = 0; i >-30 ; i--) {
            String time = TimeUtils.getdatebyDAY(i);
            time=TimeUtils.dateToStryyyymmdd( TimeUtils.date(time));
            String yytime=TimeUtils.getdatebyDAY(i);
            String name="\nPARTITION "+ptable+time+" " +
                    "\tVALUES\n" +
                    "\t\tLESS THAN ( TO_DAYS( '"+yytime+"' ) ) ENGINE = INNODB,";
            sql+=name;
        }
        sql = sql.substring(0, sql.length() - 1);
        sql+="  );";
        System.out.println(sql);

    }
}
