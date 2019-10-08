package com.hzz.srpingbootdemo;

import com.hzz.config.CompactDisc;
import com.hzz.service.HistoryGpsInfoService;
import com.hzz.service.IQueryService;
import com.hzz.springbootdao.Hzzdao;
import com.hzz.springbootdao.util.ConverMap;
import com.hzz.util.TimeUtils;
import com.hzz.util.userMap;
import net.fxft.cloud.redis.RedisUtil;
import net.fxft.common.jdbc.JdbcUtil;
import net.fxft.common.jdbc.RowDataMap;
import net.fxft.common.util.JacksonUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class historyredisTests {

    @Autowired
    private HistoryGpsInfoService historyGpsInfoService;


    @Autowired
    private RedisUtil redisUtil;

    private String gpsinfokey = "gpsinfo:";

    @Test
    public  void addredis() {
//        List<RowDataMap> da=historyGpsInfoService.searchbySimnoandtime("闽DE7555", TimeUtils.todatetime("2019-08-01 00:00:00"),TimeUtils.todatetime("2019-08-21 00:00:00"),"013301780721",0,"",0.0);
//
//        for (RowDataMap dataMap : da) {
//           String or= JacksonUtil.toJsonString(dataMap);
//           Map da1= JacksonUtil.parseJsonToMap(or);
//            System.out.println(da1);
//        }
//
//
//
//        System.out.println(da.size());
//        Map<Integer, userMap> da=new HashMap<>();
//        userMap a1=new userMap();
//        a1.setA(1);
//        a1.setName("小猫");
//        da.put(1, a1);
//        userMap a2 = da.get(1);
//        a2.setName("小狗");
//        System.out.println(a2);
        String tablename = "p_ccstatbymonth";
     //   String tablename = "p_logisticsstatistics";
        String te="";
        for (int i = 26; i >= -24; i--) {
            String datastr=TimeUtils.getdatebyMonth(new Date(),i)+"-02";
            String name=TimeUtils.getdatebyMonth2(new Date(),i);
            te+="PARTITION "+tablename+name+" VALUES LESS THAN (TO_DAYS('"+datastr+"')) ENGINE = InnoDB,";
        }
        System.out.println(te);
        RowDataMap da=null;
        System.out.println(da.getDoubleValue("cn"));
        System.out.println(1);
    }
}
