package com.hzz.backgroup;

import com.hzz.dao.AdasadsmsMapper;
import com.hzz.entity.AlarmConfig;
import com.hzz.springbootdao.Hzzdao;
import com.hzz.springbootdao.util.ConverMap;
import com.hzz.springbootdao.util.PaginateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class backhzzdb {

    @Autowired
    @Qualifier("hzzdao1")
    private Hzzdao hzzdao;

    @Autowired
    private AdasadsmsMapper adasadsmsMapper;
    @PostConstruct
    private void init(){
//        String sql="select * from alarmconfig ";
//        PaginateResult paginateResult=hzzdao.queryPage(sql, AlarmConfig.class, 10, 10);
//        PaginateResult paginateResult2=hzzdao.queryPage(sql, 10, 10);
//        List<ConverMap> lists=hzzdao.query(sql);
//        List<AlarmConfig> lists2=hzzdao.query(sql,AlarmConfig.class);
//
//        Map da = new HashMap<>();
//        da.put("type", 1);
//        List<ConverMap> name = adasadsmsMapper.selecttest(da);
//        for (ConverMap map : name) {
//            System.out.println(map.getString("time",""));
//            System.out.println(map.getDate("time",null));
//
//        }
//        System.out.println(name);
    }
}
