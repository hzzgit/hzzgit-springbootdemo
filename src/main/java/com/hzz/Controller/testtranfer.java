package com.hzz.Controller;

import com.hzz.springbootdao.Hzzdao;
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
        Vehicle vehicle = null;
//        vehicle.getCreateDate();

    }
}
