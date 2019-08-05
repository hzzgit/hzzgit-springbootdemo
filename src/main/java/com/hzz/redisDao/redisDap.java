package com.hzz.redisDao;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hzz.redis.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class redisDap {

    @Autowired
    private RedisUtil redis;
    @PostConstruct
    public void redistest2(){
        String te= (String) redis.execute(jd->{
            return jd.get("tk");
        });
        int a=0;
        System.out.println(te+"+++2");

        JSONObject jsonObject=new JSONObject();
        jsonObject.put("name","xxxx");

        redis.execute(jd->{
            jd.set("te1",jsonObject.toJSONString());
        });
    }
    @PostConstruct
    public void redistest(){

        String te= (String) redis.execute(jd->{
            return jd.get("te1");
        });
        System.out.println(te);
        JSONObject jsonObject=new JSONObject();
        jsonObject= JSON.parseObject(te);
        System.out.println(jsonObject);
    }



}
