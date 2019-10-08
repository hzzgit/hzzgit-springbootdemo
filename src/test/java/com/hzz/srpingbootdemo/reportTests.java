package com.hzz.srpingbootdemo;

import com.hzz.service.HistoryGpsInfoService;
import com.hzz.util.TimeUtils;
import net.fxft.cloud.redis.RedisUtil;
import net.fxft.common.jdbc.JdbcUtil;
import net.fxft.common.jdbc.RowDataMap;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class reportTests {

    private static final Logger logger = LoggerFactory.getLogger(reportTests.class);

    @Autowired
    private HistoryGpsInfoService historyGpsInfoService;

    @Autowired
    private JdbcUtil jdbcUtil;

    @Autowired
    private RedisUtil redisUtil;


    @Test
    public  void addredis() {

    }

    /**
     * 根据起始时间和结束时间查询出来每辆车的其他类型的次数
     *
     * @return
     */
    @Test
    public void searchmonthcity() {
        String Times="2017-06-01";
        long s=System.currentTimeMillis();   //获取开始时间
        String ccmonthsql = " select vehicleId, type,cn from gps_hisdata.Logisticsstatistics where   time =?   "; //根据车辆和类型将所有的查询出来
        List<RowDataMap> cityallcodatas = new ArrayList<>();
        RowDataMap citydatas = new RowDataMap();
        long s1=System.currentTimeMillis();   //获取开始时间
        cityallcodatas = jdbcUtil.sql(ccmonthsql).addIndexParam(Times).queryWithMap();
        long e1=System.currentTimeMillis(); //获取结束时间

        logger.debug("查询用时："+(e1-s1)+"ms");
        for (RowDataMap datum : cityallcodatas) {
            String vehicleid = datum.getStringValue("vehicleId");
            String type = datum.getStringValue("type");
            String co1 = datum.getStringValue("cn");
            String key = vehicleid + "_" + type;
            citydatas.put(key, co1);
        }
        long e=System.currentTimeMillis(); //获取结束时间

        logger.debug("查询物流报表车辆对应各参数花费时间："+(e-s)+"ms");

        System.out.println(citydatas);
    }
}
