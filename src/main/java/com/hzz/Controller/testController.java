package com.hzz.Controller;

import com.github.pagehelper.PageHelper;
import com.hzz.dao.AdasadsmsMapper;
import com.hzz.dao.basicdataMapper;
import com.hzz.entity.basicdata;
import com.hzz.redisDao.autoCache;
import com.hzz.serialize.entity.GPSRealData;
import com.hzz.serialize.entity.GpsRealDataRest;
import com.hzz.serialize.util.KryoUtil;
import com.hzz.service.IQueryService;
import com.hzz.service.PaginateResult;
import net.fxft.cloud.redis.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/testuser")
public class testController {

    @Autowired
    private basicdataMapper basicdataMapper;
    @Autowired
    private AdasadsmsMapper adasadsmsMapper;

    @Autowired
    private RedisUtil redisUtil;

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

        List<GPSRealData> gpsRealDatas=new ArrayList<>();
        for (int i = 0; i <100000 ; i++) {
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
        Map<String,byte[] > amap=new HashMap<>();
        for (GPSRealData gpsRealData : gpsRealDatas) {
                    amap.put(gpsRealData.getSimNo(),KryoUtil.object2clsbyte(gpsRealData) );
        }
          long s = System.currentTimeMillis();   //获取开始时间

        autoCache.saveCache(amap);
        long e = System.currentTimeMillis(); //获取结束时间
        System.out.println( "写入文件用时：" + (e - s) + "ms");
             new Thread(()->{
                             while (true){
                                   long s1 = System.currentTimeMillis();   //获取开始时间

                                 String key="rdtest:";
                                 redisUtil.pipeline(pl -> {//这边计算正常点
                                     for (GPSRealData gpsRealData : gpsRealDatas) {
                                         pl.setex((key+gpsRealData.getSimNo()).getBytes(),100,KryoUtil.object2clsbyte(gpsRealData));
                                     }
                                 });
                                 long e1 = System.currentTimeMillis(); //获取结束时间
                                 System.out.println("用时：" + (e1 - s1) + "ms");
                                 try {
                                     Thread.sleep(10000);
                                 } catch (InterruptedException e2) {

                                 }

                             }
                     }).start();



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
