package com.hzz.lamdba;

import com.esotericsoftware.kryo.Kryo;
import com.hzz.springbootdao.util.ConverterUtils;
import net.fxft.common.function.ThrowableConsumer;
import net.fxft.gateway.util.KryoUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Pipeline;

import java.util.*;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Consumer;
import java.util.function.Function;

public class reidshzz {
    public static JedisPool pool = null;

    static {
        JedisPoolConfig config = new JedisPoolConfig();
        //   pool = new JedisPool("localhost");
        //pool = new JedisPool(config,"r-bp143d2ed3392654.redis.rds.aliyuncs.com",6379,222321,"1jfrH8XLvx4wnbGNpY5Rr7SM",1);
        pool = new JedisPool(config, "172.30.102.42", 6379, 222321, "zYy2gV5ToZIiAs03rfwV9Btv", 1);
    }

    public static void pipine(Consumer<Pipeline> fun) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            Pipeline pipeline = jedis.pipelined();
            fun.accept(pipeline);
            pipeline.sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jedis.close();
        }
    }

    public static <T> T pipine(Function<Pipeline, T> fun) {
        Jedis jedis = null;
        T values = null;
        try {
            jedis = pool.getResource();
            Pipeline pipeline = jedis.pipelined();
            values = fun.apply(pipeline);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jedis.close();
        }
        return values;
    }

    public static <T> T excute(Function<Jedis, T> fun) {
        Jedis jedis = null;
        T a1 = null;
        try {
            jedis = pool.getResource();
            a1 = fun.apply(jedis);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jedis.close();
        }
        return a1;
    }

    public static <T> void splitLoop(Collection<T> sourceList, int splitCnt, ThrowableConsumer<Collection<T>> comsumer) throws Exception {
        if (sourceList != null && sourceList.size() > 0 && splitCnt > 0) {
            if (sourceList.size() <= splitCnt) {
                comsumer.accept(sourceList);
            } else {
                List<T> sourList = new ArrayList<>();
                for (T t : sourceList) {
                    sourList.add(t);
                }
                for (int i = 0; i < sourceList.size(); i += splitCnt) {
                    if (i + splitCnt > sourceList.size()) {
                        splitCnt = sourceList.size() - i;
                    }
                    List<T> list = sourList.subList(i, i + splitCnt);
                    comsumer.accept(list);
                }
            }
        }
    }

    public static void main(String[] args) {

        long s = System.currentTimeMillis();   //获取开始时间

        Set<String> key1s = reidshzz.excute(jedis -> {
            Set<String> keys = jedis.keys("rd:*");
            return keys;
        });

        System.out.println(key1s.size());
        List<Object> vals = new ArrayList<>();
        try {
            splitLoop(key1s, 100, arr -> {
                List<Object> vals1 = reidshzz.pipine(pipeline -> {
                    for (String key1 : arr) {
                        pipeline.get(key1.getBytes());
                    }
                    return pipeline.syncAndReturnAll();
                });
                vals.addAll(vals1);
            });
        } catch (Exception e1) {
            e1.printStackTrace();
        }

//        List<Object> vals=reidshzz.pipine(pipeline -> {
//            for (String key1 : key1s) {
//                pipeline.get(key1.getBytes());
//            }
//            return pipeline.syncAndReturnAll();
//        });
        long e = System.currentTimeMillis(); //获取结束时间

        System.out.println("用时" + (e - s) + "ms");
        List<GPSRealData> gpsRealData = new ArrayList<>();
        for (Object val : vals) {
            GPSRealData realData = KryoUtil.byte2object((byte[]) val, GPSRealData.class);
            gpsRealData.add(realData);
        }
        System.out.println(gpsRealData.size());


//        Set<String> finalKey1s = key1s;
//        reidshzz.pipine(pipeline -> {
//            for (String s : finalKey1s) {
//                pipeline.del(s);
//            }
//        });
//
//        key1s=  reidshzz.excute(jedis -> {
//            Set<String> keys= jedis.keys("*");
//            return keys;
//        });
//        System.out.println(key1s);

    }
}
