package com.hzz.srpingbootdemo;

import com.hzz.config.CompactDisc;
import com.hzz.entity.basicdata;
import com.hzz.service.IQueryService;
import com.hzz.service.moreinteface.beanclase;
import com.hzz.service.moreinteface.moreinteface;
import com.hzz.springbootdao.Hzzdao;
import com.hzz.springbootdao.Mysqldb;
import com.hzz.springbootdao.util.ConverMap;
import net.fxft.common.jdbc.ColumnSet;
import net.fxft.common.jdbc.JdbcUtil;
import net.fxft.common.jdbc.RowDataMap;
import net.fxft.common.log.AttrLog;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SrpingbootdemoApplicationTests {

    @Autowired
    private JdbcUtil jdbcUtil;

    @Autowired
    private IQueryService queryService;

    @Autowired
    private IQueryService queryService2;

    @Autowired
    @Qualifier("heyGirl")
    private CompactDisc compactDisc;

    @Autowired
    @Qualifier("hzzdao2")
    private Hzzdao hzzdao;

    @Autowired
    @Qualifier("moreService1")
    private moreinteface moreinteface;

    @Autowired
    @Qualifier("moreService2")
    private moreinteface moreinteface2;

    @Autowired
    private com.hzz.service.moreinteface.beanclase beanclase;

    @Test
    public void contextLoads() {
        beanclase.get();
    }

    //47、48、49 	超速且疲劳驾驶的时长，里程，次数
    public List<RowDataMap> checktype474849(String starttime, String endtime) throws Exception {
        String sql = "SELECT vehicleId, startTime, endTime FROM gps_hisdata.car_duration" +
                " WHERE 1 = 1 AND createdate >= ? " +
                " AND createdate < ? " +
                " AND type IN ( 4 ) and duration >= 14400  ";

        List<RowDataMap> vebytimeMap = jdbcUtil.sql(sql).addIndexParam(starttime, endtime).queryWithMap();
        List<RowDataMap> alldata = new ArrayList<>();
        for (RowDataMap rowDataMap : vebytimeMap) {
            Long vehicleId = rowDataMap.getLongValue("vehicleId");
            String startTime = rowDataMap.getStringValue("startTime");
            String endTime = rowDataMap.getStringValue("endTime");
            String sql1 = "    " +
                    "select * from ( SELECT vehicleId, sum( overSpeedMileage ) mileage, sum( duration ) duration ,count(*) co   " +
                    "FROM gps_hisdata.reportalarm WHERE 1=1   " +
                    "and ( endTime >= ?   " +
                    "AND endTime <= ?  or startTime >=? and startTime <=? )   " +
                    "and code ='local'  " +
                    "and  alarmState = 'overspeed_jr'   " +
                    "AND vehicleId = ? ) d2 where d2.co>0" ;
            RowDataMap data = jdbcUtil.sql(sql1).addIndexParam(startTime, endTime,startTime, endTime, vehicleId).queryFirstWithMap();
            if(data!=null) {
                alldata.add(data);
            }
        }
        return alldata;
    }
}
