package com.hzz.redisDao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Map;

public class autoCache {


    private static final Logger log = LoggerFactory.getLogger(autoCache.class);



    public static void saveCache(Map cmap) {
        try {
            long l1 = System.currentTimeMillis();
            FileOutputStream fos = new FileOutputStream("autoCache.cache");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(cmap);
            oos.flush();
            oos.close();
            long l2 = System.currentTimeMillis();
            log.debug( "本地缓存写入时间为" + (l2 - l1) + "ms");
        } catch (IOException e) {
            log.error("保存实时运行时间和第一次运营时间的缓存失败", e);
        }

    }

    public static  void deletecache(){
        File f = new File("autoCache.cache");
        if (f.exists()) {
            f.delete();
        }
    }

    public static void main(String[] args) {
        autoCache.deletecache();
    }


}
