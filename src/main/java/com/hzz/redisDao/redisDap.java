//package com.hzz.redisDao;
//
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONObject;
//import com.hzz.serialize.entity.AlarmConfig;
//import com.hzz.serialize.util.KryoUtil;
//import net.fxft.cloud.redis.RedisUtil;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//@Service
//public class redisDap {
//
//    @Autowired
//    private RedisUtil redis;
//
//    @Autowired
//    public void seritest(){
//        AlarmConfig alarmConfig2 = (AlarmConfig) redis.execute(jedis -> {
//            byte[] bytes = jedis.get("alarm".getBytes());
//            return KryoUtil.<AlarmConfig>clsbyte2object(bytes);
//        });
//        System.out.println(1);
//    }
//
//   // @PostConstruct
//    public void redistest2(){
//        String te= (String) redis.execute(jd->{
//            return jd.get("tk");
//        });
//        int a=0;
//        System.out.println(te+"+++2");
//
//        JSONObject jsonObject=new JSONObject();
//        jsonObject.put("name","xxxx");
//
//        redis.execute(jd->{
//            jd.set("te1",jsonObject.toJSONString());
//        });
//    }
//   // @PostConstruct
//    public void redistest(){
//
//        String te= (String) redis.execute(jd->{
//            return jd.get("te1");
//        });
//        System.out.println(te);
//        JSONObject jsonObject=new JSONObject();
//        jsonObject= JSON.parseObject(te);
//        System.out.println(jsonObject);
//    }
//
//
//
//}
