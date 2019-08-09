package com.hzz.srpingbootdemo;

import com.hzz.entity.basicdata;
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
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SrpingbootdemoApplicationTests {

    @Autowired
    private JdbcUtil jdbcUtil;

    @Autowired
    private Hzzdao hzzdao;
    @Test
    public void contextLoads() {
        try {
            String sql="select DISTINCT alarmType  as alarmType from alarmconfig";
            List<ConverMap> hzz=hzzdao.query(sql);
            String types="";
            for (ConverMap converMap : hzz) {
                String alarmType = converMap.getString("alarmType");
                types=types+alarmType+",";
            }
            System.out.println(types);
        } catch (Exception e) {

        }


    }
}
