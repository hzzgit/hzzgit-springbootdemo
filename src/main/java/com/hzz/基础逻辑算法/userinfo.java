package com.hzz.基础逻辑算法;

import com.hzz.lamdba.GPSRealData;
import com.hzz.util.TimeUtils;
import net.fxft.common.jdbc.JdbcUtil;
import net.fxft.common.jdbc.RowDataMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Service
public class userinfo {

    @Autowired
    private JdbcUtil jdbcUtil;

   // @PostConstruct
    private void init(){
        String sql="select simNo,depId,plateNo,vehicleId from vehicle where deleted =false ";
        List<RowDataMap> list = jdbcUtil.sql(sql).queryWithMap();
        String dassql="";
        for (RowDataMap s : list) {
            String simNo = s.getStringValue("simNo");
            String depId = s.getStringValue("depId");
            String plateNo = s.getStringValue("plateNo");
            String vehicleId = s.getStringValue("vehicleId");
            String sqlhql="select * from gps_hisdata.gpsinfo_20191104 where simNo ='"+simNo+"' and sendtime >='2019-11-01' order by sendTime desc limit 0,1";
            GpsInfo gpsInfo = jdbcUtil.sql(sqlhql).queryFirst(GpsInfo.class);

            if(gpsInfo!=null) {
                String sql1 = "INSERT INTO `subiaodb`.`gpsrealdata` (`ID`, `alarmState`, `altitude`, `depId`, `direction`, `dvrStatus`, `gas`, `latitude`, `location`, `longitude`, `mileage`, `online`, `plateNo`, `recordVelocity`, `sendTime`, `simNo`, `status`, `updateDate`, `valid`, `vehicleId`, `velocity`, `overSpeedAreaId`, `overSpeedAreaType`, `routeAlarmType`, `routeSegmentId`, `runTimeOnRoute`, `onlineDate`, `areaAlarm`, `areaId`, `areaType`, `parkingTime`, `busDirection`, `lastStationId`, `signalState`, `tiredAlarmTime`, `lastDayMileage`, `arriveKeyPlaceAlarm`, `leaveKeyPlaceAlarm`, `mapAreaAlarm`, `offsetRouteAlarm`, `overSpeedAlarm`, `tiredLevel`, `unusualDriveBeaviour`, `videoAlarm`, `videoCoverAlarmStatus`, `videoLossAlarmStatus`, `videoStorageFaultAlarmStatus`, `accOffTime`, `batteryData`, `clockWiseRunning`, `overload`) VALUES (0, '00000000000000000000100000000000', '" + gpsInfo.getAltitude() + "', '" + depId + "', '" + gpsInfo.getDirection() + "', NULL, '0.00', '" + gpsInfo.getLatitude() + "', NULL, '" + gpsInfo.getLongitude() + "', '" + gpsInfo.getMileage() + "', '\\0', '" + plateNo + "', '" + gpsInfo.getRecordVelocity() + "', '" + TimeUtils.dateTodetailStr(gpsInfo.getSendTime()) + "', '" + simNo + "', '" + gpsInfo.getStatus() + "', '" + TimeUtils.dateTodetailStr(gpsInfo.getSendTime()) + "', '\u0001', '" + vehicleId + "', '" + gpsInfo.getVelocity() + "', '0', '0', '0', '0', '0', '" + TimeUtils.dateTodetailStr(gpsInfo.getSendTime()) + "', '0', '0', '0', '2019-08-12 09:38:25', NULL, NULL, '0', NULL, '0', NULL, NULL, NULL, NULL, NULL, '0', '0', '4', '0', '0', '1', NULL, NULL, '0', '0');";
                dassql += sql1;
                System.out.println(sql1);
            }
        }

        File file = new File("D:test2.txt");
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            FileWriter fw = new FileWriter("D:test2.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(dassql);// 往已有的文件上添加字符串
            bw.close();
            fw.close();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println(1);
    }
}
