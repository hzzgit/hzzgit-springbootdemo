package com.hzz.util;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class autoCache {

    public Map<String, String> cmap = new HashMap<>();



    public void loadCache() {
        try {
            File f = new File("autoCache.cache");
            if (f.exists()) {
                FileInputStream fis = new FileInputStream(f);
                ObjectInputStream ois = new ObjectInputStream(fis);
                Object obj = ois.readObject();
                ois.close();
                this.cmap = (Map<String, String>) obj;
                System.out.println(cmap);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    public void saveCache() {
        try {
            long l1 = System.currentTimeMillis();
            FileOutputStream fos = new FileOutputStream("autoCache.cache");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(cmap);
            oos.flush();
            oos.close();
            long l2 = System.currentTimeMillis();
        } catch (IOException e) {
        }

    }


    public static void main(String[] args) {
        autoCache autoCache=new autoCache();
        autoCache.loadCache();

    }

}
