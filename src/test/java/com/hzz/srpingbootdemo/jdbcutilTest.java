package com.hzz.srpingbootdemo;

import net.fxft.common.jdbc.JdbcUtil;
import net.fxft.common.jdbc.RowDataMap;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SrpingbootdemoApplication.class)
public class jdbcutilTest {

    @Autowired
    private JdbcUtil   jdbcUtil;

    @Test
    public void tes1(){
        String sql = "select * from vehicle where 1=1 and vehicleId in ?";
        List<String> vehicleIds = new ArrayList<>();
        vehicleIds.add("22026");
        vehicleIds.add("22027");
        List<RowDataMap> rowDataMaps = jdbcUtil.sql(sql).addIndexParam(vehicleIds).queryWithMap();
        for (RowDataMap rowDataMap : rowDataMaps) {
            System.out.println(rowDataMap.size());
        }
    }
}
