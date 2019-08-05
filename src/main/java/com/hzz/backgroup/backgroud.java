//package com.hzz.backgroup;
//
//import net.fxft.common.jdbc.JdbcUtil;
//import net.fxft.common.jdbc.ConverMap;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class backgroud {
//
//    @Autowired
//    private JdbcUtil jdbcUtil;
//
//   // @PostConstruct
//    public void init (){
//        String sql="select v.vehicleid,d.depid from vehicle v  left join department d on v.depid=d.depid  ";
//       List<ConverMap> datas= jdbcUtil.sql(sql).queryWithMap();
//        for (ConverMap data : datas) {
//            long vehicleid=data.getLongValue("vehicleid");
//            long depid=data.getLongValue("depid");
//            Ccstatbycity ccstatbycity=new Ccstatbycity();
//            ccstatbycity.setVehicleid(vehicleid);
//            ccstatbycity.setDepid(depid);
//            ccstatbycity.setCo1((int)(1+Math.random()*100) );
//            ccstatbycity.setTime("2019-06");
//            ccstatbycity.setName("福州");
//            ccstatbycity.setType(1);
//            jdbcUtil.insert(ccstatbycity).execute();
//
//
//             ccstatbycity=new Ccstatbycity();
//            ccstatbycity.setVehicleid(vehicleid);
//            ccstatbycity.setDepid(depid);
//            ccstatbycity.setCo1((int)(1+Math.random()*100) );
//            ccstatbycity.setTime("2019-06");
//            ccstatbycity.setName("福州-厦门");
//            ccstatbycity.setType(2);
//            jdbcUtil.insert(ccstatbycity).execute();
//        }
//    }
//}
