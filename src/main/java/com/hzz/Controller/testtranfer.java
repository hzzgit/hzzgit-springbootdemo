package com.hzz.Controller;

import com.hzz.springbootdao.Hzzdao;
import com.hzz.springbootdao.Mysqldb;
import net.fxft.common.jdbc.JdbcUtil;
import net.fxft.gateway.po.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class testtranfer {

    @Autowired
    @Qualifier("hzzdaonew")
    private Hzzdao hzzdao;

    @Transactional
    public void tett() throws Exception{
        String sql="insert into bigscreen_log(id,tag,keyname ) values(0,1,1)";
        hzzdao.executesql(sql);
        Vehicle vehicle = null;
//        vehicle.getCreateDate();

    }
}
