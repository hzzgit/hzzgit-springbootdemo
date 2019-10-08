package com.hzz.service.impl;


import com.hzz.service.HistoryGpsInfoService;
import com.ltmonitor.util.StringUtil;
import net.fxft.ascsgpsproc.api.GpsInfoService;
import net.fxft.ascsgpsproc.api.TimeUtils;
import net.fxft.common.jdbc.RowDataMap;
import net.fxft.common.log.AttrLog;
import net.fxft.common.util.BasicUtil;
import net.fxft.gateway.util.PointLatLng;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Service
public class HistoryGpsInfoServiceimp implements HistoryGpsInfoService {

    private static final Logger logger = LoggerFactory.getLogger(HistoryGpsInfoServiceimp.class);

    @Autowired
    private GpsInfoService gpsInfoServiceimp;



    /**
     * 历史轨迹实际获取的service层
     *
     * @param plateNo           车牌号
     * @param startTime         开始时间
     * @param endTime           结束时间
     * @param simNo             simno卡号
     * @param minSpeed          最小速度
     * @param mapType           地图类型
     * @param mileageadjustment 里程调整百分比
     * @return
     */
    public List<RowDataMap> searchbySimnoandtime(String plateNo, Date startTime, Date endTime, String simNo, int minSpeed, String mapType, double mileageadjustment) {
              AttrLog alog = AttrLog.get("历史轨迹分页查询报错")
                              .log("simNo", simNo)
                              .log("startTime", startTime)
                          .log("endTime",endTime);

        try {
            long l1 = System.currentTimeMillis();
            List<Map<String, Date>> timedates = TimeUtils.getdataallday(startTime, endTime);
            List<RowDataMap> date = new ArrayList<>();
            List<RowDataMap>[] lindata=new ArrayList[timedates.size()];
            int threadco = 2;
            if (timedates.size() > 10) {//限制线程池最大为10，不能超过这个数量，避免服务器压力过大
                threadco = 10;
            } else {
                threadco = timedates.size();
            }
            if (threadco == 1) {
                threadco = 2;
            }
            ExecutorService executorService = Executors.newFixedThreadPool(threadco);// 线程池.大小根据有多少天
            String finalSimNo = simNo;
            for (int i = 0; i < timedates.size(); i++) {
                Map<String, Date> timedate = timedates.get(i);
                int finalI = i;
                executorService.submit(new Thread(new Runnable() {
                    public void run() {
                        Date startTime = timedate.get("startTime");//模拟前端的每日间隔时间查询的开始时间
                        Date endTime = timedate.get("endTime");
                        List<RowDataMap> date1 = gpsInfoServiceimp.searchgpsinfobysubmeter(startTime, endTime, finalSimNo, minSpeed, false);//进行查询每日，不进行排序
                        lindata[finalI]=date1;
                    }
                }));
            }
            executorService.shutdown();
            try {
                executorService.awaitTermination(600, TimeUnit.SECONDS);
            } catch (InterruptedException e) {

                e.printStackTrace();
            }
            for (List<RowDataMap> lindatum : lindata) {
                if(lindatum!=null&&lindatum.size()>0) {
                    date.addAll(lindatum);
                }
            }
            Collections.sort(date, new Comparator<RowDataMap>() {//线程池跑完，根据时间进行升序
                        @Override
                        public int compare(RowDataMap o1, RowDataMap o2) {
                            Date sendTime = o1.getDateValue("sendTime");
                            Date sendTime2 = o2.getDateValue("sendTime");
                            return sendTime.compareTo(sendTime2);
                        }
                    }
            );
            long l2 = System.currentTimeMillis();
            logger.debug("历史轨迹请求时间：" + (l2 - l1));
            return date;
        } catch (Exception e) {
            alog.log("轨迹轨迹分天查询", BasicUtil.exceptionMsg(e));
        }finally {
            logger.debug(alog.toString());

        }
        return new ArrayList<>();
    }


}
