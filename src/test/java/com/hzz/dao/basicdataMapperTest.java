package com.hzz.dao;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hzz.entity.AlarmConfig;
import net.fxft.common.jdbc.RowDataMap;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class basicdataMapperTest {

    @Resource
    private AlarmConfigMapper alarmConfigMapper;

    @Test
    public void test(){
        AlarmConfig alarmConfig = alarmConfigMapper.selectById(215);
        AlarmConfig config=alarmConfigMapper.searchbyalarmtype("1");
        Page<AlarmConfig> page = new Page<>(1,10);
        List<RowDataMap> selectrowdata = alarmConfigMapper.selectrowdata(page);
        int co=alarmConfigMapper.selectco();
        System.out.println(alarmConfig);
    }

    @Test
    public void testselectMapper(){
        LambdaQueryWrapper<AlarmConfig> lambdaQueryWrapper =new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(AlarmConfig::getAlarmtype,"1");
        Page<AlarmConfig> page = new Page<>(1,10);
        IPage<AlarmConfig> alarmConfigIPage = alarmConfigMapper.selectPage(page, lambdaQueryWrapper);
        System.out.println(1);
    }

}
