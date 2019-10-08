package com.hzz.service;

import net.fxft.common.jdbc.RowDataMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

public interface HistoryGpsInfoService {


    /**
     * 历史轨迹实际获取的service层
     * @param plateNo 车牌号
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @param simNo simno卡号
     * @param minSpeed 最小速度
     * @param mapType 地图类型
     * @param mileageadjustment 里程调整百分比
     * @return
     */
    public List<RowDataMap> searchbySimnoandtime(String plateNo, Date startTime, Date endTime, String simNo, int minSpeed, String mapType, double mileageadjustment);

}
