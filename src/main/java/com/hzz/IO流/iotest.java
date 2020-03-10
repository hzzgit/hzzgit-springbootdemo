package com.hzz.IO流;

import sun.applet.Main;

import java.io.File;

public class iotest {
    public static void main(String[] args) {
        File file = new File("C:\\Users\\Administrator\\Desktop\\发给宁夏的web端代码");

        iotest.getfileall(file);
    }
   public static void getfileall( File file ){
       if(file.isDirectory()){
           File[] files = file.listFiles();
           for (File file1 : files) {
             if(file1.isDirectory()){
                 System.out.println(file1);

                // getfileall(file1);
             }else{
             }
           }

       }
   }


}
