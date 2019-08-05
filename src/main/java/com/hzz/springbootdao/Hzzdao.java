package com.hzz.springbootdao;

import com.hzz.springbootdao.util.ConverMap;
import com.hzz.springbootdao.util.PaginateResult;

import java.util.ArrayList;
import java.util.List;

public interface Hzzdao {

    /**
     * 查询分页的数据和数量的类
     * @param sql
     * @param object2
     * @param page
     * @param pagesize
     * @param wdata
     * @param <T>
     * @return
     */
    public <T> PaginateResult queryPage(String sql, Class<T> object2, int page, int pagesize,
                                        Object... wdata);

    /**
     * 根据类进行不分页查询
     * @param sql
     * @param object2
     * @param wdata
     * @return
     */
    public <T> List<T> query(String sql, Class<T> object2, Object... wdata);


    /**
     * 查询分页的数据和数量的集合
     * @param sql
     * @param page
     * @param pagesize
     * @param wdata
     * @return
     */
    public  PaginateResult queryPage(String sql,  int page, int pagesize,
                                          Object... wdata);



    /**
     * 根据强转Map的集合进行不分页查询
     * @param sql
     * @param wdata
     * @return
     */
    public List<ConverMap> query(String sql, Object... wdata);



    public boolean executesql(String sql, Object... wdata);

    public boolean executesql(ArrayList listsql);


}
