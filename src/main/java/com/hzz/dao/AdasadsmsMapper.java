package com.hzz.dao;

import com.hzz.entity.Adasadsms;
import com.hzz.springbootdao.util.ConverMap;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface AdasadsmsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Adasadsms record);

    int insertSelective(Adasadsms record);

    Adasadsms selectByPrimaryKey(Integer id);

    List<Adasadsms> selectalarm();

    int updateByPrimaryKeySelective(Adasadsms record);

    int updateByPrimaryKey(Adasadsms record);

    @Select("select * from gps_hisdata.ccstatbymonth where type =#{type} ")
    List<ConverMap> selecttest(Map data);
}