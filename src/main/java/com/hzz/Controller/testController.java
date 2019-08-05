package com.hzz.Controller;

import com.github.pagehelper.PageHelper;
import com.hzz.dao.AdasadsmsMapper;
import com.hzz.dao.basicdataMapper;
import com.hzz.entity.UserInfo;
import com.hzz.entity.basicdata;
import com.hzz.service.IQueryService;
import com.hzz.service.PaginateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    private IQueryService queryService;


    @RequestMapping("/mybatistest")
    public PaginateResult removeuser(){
        PageHelper.startPage(1,10,true);
        List<basicdata> list= basicdataMapper.selects();
        return queryService.queryByPagination(list);
    }



}
