package com.hzz.dao;

import com.hzz.entity.basicdata;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface basicdataMapper {
    int deleteByPrimaryKey(Integer baseid);

    int insert(basicdata record);

    int insertSelective(basicdata record);

    basicdata selectByPrimaryKey(Integer baseid);

    int updateByPrimaryKeySelective(basicdata record);

    int updateByPrimaryKey(basicdata record);
    List<basicdata> selects();
}