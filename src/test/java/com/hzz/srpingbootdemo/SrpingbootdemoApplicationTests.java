//package com.hzz.srpingbootdemo;
//
//import com.hzz.entity.VehicleData;
//import com.hzz.entity.Vehicleregisterinfo;
//import net.fxft.common.jdbc.ColumnSet;
//import net.fxft.common.jdbc.JdbcUtil;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.Date;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class SrpingbootdemoApplicationTests {
//
//    @Autowired
//    private JdbcUtil jdbcUtil;
//
//    @Test
//    public void contextLoads() {
//        try {
//
//            String sql = "SELECT * from vehicleregisterinfo where vehicleId =?";
//            Vehicleregisterinfo vehicleregisterinfo = jdbcUtil.sql(sql).addIndexParam(1).queryFirst(Vehicleregisterinfo.class);
//            System.out.println(vehicleregisterinfo);
//            vehicleregisterinfo.setPlatecolor(222);
//            vehicleregisterinfo.setUpdatedate(new Date());
//            jdbcUtil.defaultDao(Vehicleregisterinfo.class).update(vehicleregisterinfo);
//            vehicleregisterinfo.setVehicleid(3);
//            jdbcUtil.insert(vehicleregisterinfo).execute();
//
//        } catch (Exception e) {
//
//        }
//    }
//
//}
