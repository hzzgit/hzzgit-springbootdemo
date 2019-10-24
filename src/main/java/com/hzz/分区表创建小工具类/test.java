package com.hzz.分区表创建小工具类;

import com.hzz.util.TimeUtils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class test {
    public static void main(String[] args) {
        String ptable="p_gpscompress";
        String sql="";
        for (int i = 30; i >-100 ; i--) {
            String time = TimeUtils.getdatebyMonth2(new Date(),i);
            String yytime=TimeUtils.getdatebyMonth(new Date(),i-1);
            String name="PARTITION "+ptable+time+" " +
                    "\tVALUES\n" +
                    "\t\tLESS THAN ( TO_DAYS( '"+yytime+"-01' ) ) ENGINE = INNODB,\n";
            sql+=name;
        }

        System.out.println(sql);

        File file = new File("D:test.txt");
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            FileWriter fw = new FileWriter("D:test.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(sql);// 往已有的文件上添加字符串
            bw.close();
            fw.close();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
