package com.hzz.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hzz.entity.AlarmConfig;
import net.fxft.common.jdbc.RowDataMap;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface AlarmConfigMapper extends BaseMapper<AlarmConfig> {

    @Select("select * from alarmconfig where alarmType=#{alarmType}")
    public AlarmConfig searchbyalarmtype(String alarmType);

    @Select("select v.plateNo,d.name from vehicle v,department d where v.depId =d.depId ")
    public List<RowDataMap> selectrowdata(IPage page);

    @Select("select count(1) cn from vehicle v,department d where v.depId =d.depId  limit 0,10")
    public int selectco();
}
