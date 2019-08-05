package com.hzz.service.impl;

import com.github.pagehelper.PageInfo;
import com.hzz.entity.basicdata;
import com.hzz.service.IQueryService;
import com.hzz.service.PaginateResult;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("queryserviceimpl")
public class queryServiceimpl implements IQueryService {

    @Override
    public PaginateResult queryByPagination(List list) {
        PageInfo<basicdata> pageInfo = new PageInfo<basicdata>(list);
        PaginateResult paginateResult=new PaginateResult(pageInfo.getTotal(),pageInfo.getList());
        return  paginateResult;
    }

}
