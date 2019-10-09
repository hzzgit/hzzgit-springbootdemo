package com.hzz.lamdba;

import com.esotericsoftware.kryo.Kryo;
import com.hzz.daoconfig.HzzDataSource;
import com.hzz.daoconfig.mysqldb;
import com.hzz.springbootdao.util.ConverterUtils;
import net.fxft.common.function.ThrowableConsumer;
import net.fxft.common.jdbc.ConnectionSource;
import net.fxft.common.jdbc.JdbcUtil;
import net.fxft.common.util.JacksonUtil;
import net.fxft.gateway.util.KryoUtil;
import net.sf.jsqlparser.expression.operators.relational.LikeExpression;
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
    public static mysqldb mysql = null;

    static {
        HzzDataSource.createhzzdb(HzzDataSource.MYSQL, HzzDataSource.web);
        mysql = new mysqldb();
        JedisPoolConfig config = new JedisPoolConfig();
       pool = new JedisPool("localhost");
        //pool = new JedisPool(config,"r-bp143d2ed3392654.redis.rds.aliyuncs.com",6379,222321,"1jfrH8XLvx4wnbGNpY5Rr7SM",1);
      //  pool = new JedisPool(config, "172.30.102.42", 6379, 222321, "zYy2gV5ToZIiAs03rfwV9Btv", 1);
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

    public static void excute(Consumer<Jedis> fun) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            fun.accept(jedis);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jedis.close();
        }
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

    public static <T> void splitLoop(Collection<T> sourceList, int splitCnt, ThrowableConsumer<Collection<T>> comsumer) {
        try {
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //将所有key都删除
    public void delall(){
        Set<String> keyals = reidshzz.excute(jedis -> {
            Set<String> keys = jedis.keys("vio:*");
            return keys;
        });
        pipine(pipeline -> {
            for (String keyal : keyals) {
                pipeline.del(keyal);
            }
            pipeline.sync();
        });
    }

    //这边是测试实时数据获取
    public void getgpsrealdata(){
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
        long e = System.currentTimeMillis(); //获取结束时间

        System.out.println("用时" + (e - s) + "ms");
        List<GPSRealData> gpsRealData = new ArrayList<>();
        for (Object val : vals) {
            GPSRealData realData = KryoUtil.byte2object((byte[]) val, GPSRealData.class);
            gpsRealData.add(realData);
        }
        System.out.println(gpsRealData.size());
    }


    //这边是测试车辆缓存的存取
    public void vehicleceche(){

//        String sql = "select * from vehicle where deleted=false ";
//        List<VehicleData> vehicleData = mysql.searchnopagesqlclass(sql, VehicleData.class);
//
//        long s5 = System.currentTimeMillis();   //获取开始时间
//        if (ConverterUtils.isList(vehicleData)) {
//            splitLoop(vehicleData, 1000, arr -> {
//                pipine(pipeline -> {
//                    for (VehicleData vehicleDatum : arr) {
//                        pipeline.rpush("vehicle", JacksonUtil.toJsonString(vehicleDatum));
//                    }
//                    pipeline.sync();
//                });
//            });
//        }
//        long e5 = System.currentTimeMillis(); //获取结束时间
//        System.out.println("插入车辆缓存到redis以数据的形式运行时间：" + (e5 - s5) + "ms");
//
//
        //        long s61 = System.currentTimeMillis();   //获取开始时间
//        List<String>  vehicleList = excute(jedis -> {
////            List<VehicleData> vehicleData1=new ArrayList<>();
//            List<String> list = jedis.lrange("vehicle", 0, -1);
////            for (String s1 : list) {
////                vehicleData1.add(JacksonUtil.parseJsonString(s1, VehicleData.class));
////            }
//            return list;
//        });
//        long e61 = System.currentTimeMillis(); //获取结束时间
//
//        System.out.println("集合的形式取出车辆缓存到redis运行时间：" + (e61 - s61) + "ms"); //这边是600MS


//        long s52 = System.currentTimeMillis();   //获取开始时间
//
//        if (ConverterUtils.isList(vehicleData)) {
//            splitLoop(vehicleData, 1000, arr -> {
//                pipine(pipeline -> {
//                    for (VehicleData vehicleDatum : arr) {
//                        pipeline.set(("vehicle:"+vehicleDatum.getVehicleId()).getBytes(), KryoUtil.object2byte(vehicleDatum));
//                    }
//                    pipeline.sync();
//                });
//            });
//        }
//        long e52 = System.currentTimeMillis(); //获取结束时间
//        System.out.println("插入车辆缓存到redis以毽子对的形式，运行时间：" + (e52 - s52) + "ms");


        long s6 = System.currentTimeMillis();   //获取开始时间
        Set<String> vekeys = reidshzz.excute(jedis -> {
            Set<String> keys = jedis.keys("vehicle:*");
            return keys;
        });
        List<Object> ves=  pipine(pipeline -> {
            for (String vekey : vekeys) {
                pipeline.get(vekey.getBytes());
            }
            return pipeline.syncAndReturnAll();
        });
        for (Object ve : ves) {
           VehicleData vehicle= KryoUtil.byte2object((byte[]) ve,VehicleData.class) ;
        }
        long e6 = System.currentTimeMillis(); //获取结束时间

        System.out.println("毽子对取出车辆缓存到redis运行时间：" + (e6 - s6) + "ms"); //这边是400ms
        System.out.println(1);
    }


    public static void main(String[] args) {
       new reidshzz().delall();

       // new reidshzz().vehicleceche();

    }
}
