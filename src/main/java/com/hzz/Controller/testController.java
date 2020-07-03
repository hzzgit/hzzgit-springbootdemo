package com.hzz.Controller;

import com.github.pagehelper.PageHelper;
import com.hzz.dao.AdasadsmsMapper;
import com.hzz.dao.basicdataMapper;
import com.hzz.entity.basicdata;
import com.hzz.serialize.entity.GPSRealData;
import com.hzz.serialize.entity.GpsRealDataRest;
import com.hzz.serialize.util.KryoUtil;
import com.hzz.service.IQueryService;
import com.hzz.service.PaginateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/testuser")
public class testController {

    @Autowired
    private basicdataMapper basicdataMapper;
    @Autowired
    private AdasadsmsMapper adasadsmsMapper;

    @Autowired
    private IQueryService queryService;
    @Autowired
    private testtranfer testtranfer;
    /**
     * 测试下是否可以用事务
     */
    @RequestMapping("/testtranfer.action")
    @ResponseBody
    public void testtranfer() {
        try {
            testtranfer.tett();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private               List<GPSRealData> gpsRealData2=new ArrayList<>();

    @PostConstruct
    private void init(){
             new Thread(()->{
                             while (true){
                                 try {
                                     double totalmemory=  Runtime.getRuntime().maxMemory()/1024/1024;
                                     double freememory=  Runtime.getRuntime().freeMemory()/1024/1024;
                              //       System.out.println("当前剩余内存"+freememory+"mb");
                                  //   System.out.println("当前总内存"+totalmemory+"mb");
                                     Thread.sleep(1000);
                                 } catch (InterruptedException e) {
                                     e.printStackTrace();
                                 }
                             }
                     }).start();
        List<GPSRealData> gpsRealDatas=new ArrayList<>();
        for (int i = 0; i <2000000 ; i++) {
            GPSRealData gpsRealData=new GPSRealData();
            gpsRealData.setId(i);
            gpsRealData.setLatitude(12212212.31331);
            gpsRealData.setAltitude(122122121.31331);
            gpsRealData.setAlarmState("21212133");
            gpsRealData.setOnline(true);
            gpsRealData.setSimNo("1213314"+i);
            gpsRealDatas.add(gpsRealData);
        }
        gpsRealData2=gpsRealDatas;

    }

    @RequestMapping("/mybatistest")
    public PaginateResult removeuser(){
        PageHelper.startPage(1,10,true);
        List<basicdata> list= basicdataMapper.selects();
        return queryService.queryByPagination(list);
    }

    @RequestMapping("/searchkryo.action")
    public byte[] searchkryo(){
        GpsRealDataRest gpsRealDataRest=new GpsRealDataRest();
        gpsRealDataRest.setGpsRealDataList(gpsRealData2);
        return KryoUtil.object2clsbyte(gpsRealDataRest);

    }



}
