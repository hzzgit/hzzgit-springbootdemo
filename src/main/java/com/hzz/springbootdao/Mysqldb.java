package com.hzz.springbootdao;


import com.baomidou.mybatisplus.annotation.TableName;
import com.hzz.entity.AlarmConfig;
import com.hzz.springbootdao.util.ConverMap;
import com.hzz.springbootdao.util.PaginateResult;
import com.hzz.util.ConverterUtils;
import lombok.extern.slf4j.Slf4j;
import net.fxft.common.util.StringUtil;

import javax.sql.DataSource;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


/**
 * 进行gbase,mysql数据库查询
 */
//@Service
    @Slf4j
public class Mysqldb implements Hzzdao {

//    @Qualifier("dataSource")
//    @Autowired
    private DataSource dataSource;

    private ConnectionhzzSource connSource;

    public Mysqldb() {
    }

    public Mysqldb(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Mysqldb(DataSource dataSource, ConnectionhzzSource connSource) {
        this.dataSource = dataSource;
        this.connSource = connSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * 根据类进行不分页查询
     * @param sql
     * @param object2
     * @param wdata
     * @return
     */
    public <T> List<T> query(String sql, Class<T> object2, Object... wdata) {
        return  searchnopagesqlclass(sql,object2,wdata);
    }

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
                                    Object... wdata){
        List<T> pages=queryByPage(sql,object2,page,pagesize,wdata);
        int co = queryByCount(getpagesqlCount(sql), wdata);
        PaginateResult paginateResult = new PaginateResult(co, pages);
        return paginateResult;
    }


    /**
     * 根据强转Map的集合进行不分页查询
     * @param sql
     * @param wdata
     * @return
     */
    public List<ConverMap> query(String sql, Object... wdata) {
        return  searchnopagesqlobject(sql,wdata);
    }

    /**
     * 查询分页的数据和数量的集合
     * @param sql
     * @param page
     * @param pagesize
     * @param wdata
     * @return
     */
    public PaginateResult queryPage(String sql, int page, int pagesize,
                                         Object... wdata){
        List<ConverMap> pages=queryByPage(sql,page,pagesize,wdata);
        int co = queryByCount(getpagesqlCount(sql), wdata);
        PaginateResult paginateResult = new PaginateResult(co, pages);
        return paginateResult;
    }

    public void insertList(List<Object> objects){
        List<String> sqlList=new ArrayList<>();
        for (Object object : objects) {
            String sql = getinsertsql(object);
            sqlList.add(sql);
        }
        if(sqlList!=null&&sqlList.size()>0) {
          boolean arg=  executesql( sqlList);
          if(arg==false){
              log.error("执行失败");
          }
        }else{
            log.error("sql=不能为空");
        }
    }

    public void insert(Object object){
        String sql = getinsertsql(object);
        if(!StringUtil.isEmpty(sql)) {
            executesql(sql);
        }else{
            log.error("sql="+sql+"不能为空");
        }
    }

    //根据实体类进行保存
    public String getinsertsql(Object object){
        if(object==null){
            throw new  NullPointerException();
        }
        String sql ="";
        try {
            Class<? extends Object> c = object.getClass();
            TableName annotation = c.getAnnotation(TableName.class);
            String tableName=annotation.value();
            Field[] declaredFields = c.getDeclaredFields();// 获取所有的变量名
            if(declaredFields.length>0) {
                 sql = "insert into " + tableName + " ( ";
                 String fielNames="";
                 String valueNames="";
                for (Field declaredField : declaredFields) {
                    int modifiers = declaredField.getModifiers();
                    if(modifiers==2) {
                        declaredField.setAccessible(true);
                        String fieldName = declaredField.getName();
                        fielNames += fieldName + ",";
                        valueNames += "'" + declaredField.get(object) + "',";
                    }
                }
                fielNames=fielNames.substring(0,fielNames.length()-1);
                valueNames=valueNames.substring(0,valueNames.length()-1);
                sql+=fielNames+" ) values("+valueNames+") ";
            }else{
                throw new  NullPointerException();
            }
        } catch (Exception e){
            log.error("插入错误,"+sql,e);
        }
        return sql;
    }

    public static void main(String[] args) {
        AlarmConfig alarmConfig = new AlarmConfig();
        alarmConfig.setId(0);
        new Mysqldb().insert(alarmConfig);
    }

    /**
     * 查询第一行第一列数据
     * @param sql
     * @param wdata
     * @return
     */
    public   Object searchFirstVal (String sql, Object... wdata) {
        PreparedStatement ps=null;
        ResultSet rs=null;
        Connection con = null;
        try {
            con = dataSource.getConnection();
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        Object result=null;
        try {
            ps = con.prepareStatement(sql);
            for (int i = 0; i < wdata.length; i++) {
                ps.setObject(i + 1, wdata[i]);
            }
            rs = ps.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            int colnum = rsmd.getColumnCount();
            if (rs.next()) {
                result= rs.getObject(1);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            this.close(rs,ps,con);
        }
        return result;
    }


    public boolean executesql(List<String> listsql) {
        boolean arg = false;
        PreparedStatement ps=null;
        ResultSet rs=null;
        Connection con = null;
        try {
            con = dataSource.getConnection();
            con.setAutoCommit(false);
            for (Object object : listsql) {
                String sql = (String) object;
                ps = con.prepareStatement(sql);
                arg = ps.execute();

            }
            con.commit();
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
            try {
                con.rollback();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
          return  false;
        } finally {
            this.close(rs,ps,con);
        }
        return arg;
    }


    public boolean executesql(String sql, Object... wdata) {
        boolean arg = false;
        PreparedStatement ps=null;
        Connection con = null;
        try {
            con=  this.connSource.getConnection();
//            con.setAutoCommit(false);
            ps = con.prepareStatement(sql);
            for (int i = 0; i < wdata.length; i++) {
                ps.setObject(i + 1, wdata[i]);
            }
            arg = ps.execute();
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } finally {
            this.connSource.close(ps, con);
        }
        return arg;
    }


    /**
     * 根据查询条件自动组装成类
     * @param sql
     * @param object2
     * @param wdata
     * @param <T>
     * @return
     */
    private <T> List<T>searchnopagesqlclass(String sql, Class<T> object2, Object... wdata) {
        PreparedStatement ps=null;
        ResultSet rs=null;
        Connection con = null;
        try {
            con=dataSource.getConnection();
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        ArrayList jsonArray = new ArrayList();
        try {
            ps = con.prepareStatement(sql);
            for (int i = 0; i < wdata.length; i++) {
                ps.setObject(i + 1, wdata[i]);
            }
            rs = ps.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            int colnum = rsmd.getColumnCount();
            //Class<? extends Object> c2 = object2.getClass();
            while (rs.next()) {
                Object object = object2.newInstance();// 创建新的对象
                Class<? extends Object> c = object.getClass();
                Field[] declaredFields = c.getDeclaredFields();// 获取所有的变量名
                for (int i = 0; i < declaredFields.length; i++) {
                    Field field = declaredFields[i];
                    String filename = field.getName();// 获取变量名
                    filename = filename.substring(0, 1).toUpperCase() + filename.substring(1);
                    Method methods2;
                    try {
                        methods2 = c.getMethod("set" + filename, field.getType());// 注意参数不是String,是string
                        // 类型
                        Object colval = rs.getObject(filename);
                        methods2.invoke(object, colval);// 通过对象，调用有参数的方法
                        // 如果这个地方需要持久保存，那么就是object类放进去。不然就是加上c.newInstance()
                    } catch (Exception e) {

                    }

                }
                jsonArray.add(object);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            this.close(rs,ps,con);
        }

        // System.out.print(cols.toString());
        return (List<T>) jsonArray;
    }


    /**
     * 查询出快速转换类型的Map集合
     * @param sql
     * @param wdata
     * @return
     */
    private  List<ConverMap> searchnopagesqlobject(String sql, Object... wdata) {
        PreparedStatement ps=null;
        ResultSet rs=null;
        Connection con = null;
        try {
            con = dataSource.getConnection();
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        ArrayList<ConverMap> jsonArray = new ArrayList();
        try {

            ps = con.prepareStatement(sql);
            for (int i = 0; i < wdata.length; i++) {
                ps.setObject(i + 1, wdata[i]);
            }
            rs = ps.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            int colnum = rsmd.getColumnCount();
            while (rs.next()) {
                ConverMap jObject = new ConverMap();
                for (int i = 1; i <= colnum; i++) {
                    String colname = rsmd.getColumnName(i);
                    Object colval = rs.getObject(i);
                    jObject.put(colname, colval);
                }
                jsonArray.add(jObject);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        finally {
            this.close(rs,ps,con);
        }
        // System.out.print(cols.toString());
        return jsonArray;
    }





    /**
     * 分页查询
     * @param sql
     * @param object2
     * @param page
     * @param pagesize
     * @param wdata
     * @return
     */
    private <T> List<T> queryByPage(String sql, Class<T> object2, int page, int pagesize,
                                    Object... wdata) {
        String sqlpage = getPageSql(sql, page, pagesize);
        return  searchnopagesqlclass(sqlpage,object2,wdata);
    }

    /**
     * 分页查询,返回Map集合
     * @param sql
     * @param page
     * @param pagesize
     * @param wdata
     * @return
     */
    private List<ConverMap> queryByPage(String sql, int page, int pagesize,
                                    Object... wdata) {
        String sqlpage = getPageSql(sql, page, pagesize);
        return  searchnopagesqlobject(sqlpage,wdata);
    }


    /**
     * 查询总数
     * @param sql
     * @param wdata
     * @return
     */
    private int queryByCount(String sql, Object... wdata) {
        int co= ConverterUtils.toInt(searchFirstVal(sql,wdata),0);
        return  co;
    }



    /**
     * 拼装数量sql
     * @param sql
     * @return
     */
    private String getpagesqlCount(String sql){
        String sql1 = "select count(*) cn from ( ";
        sql1 += sql;
        sql1 += " ) sdasgasgasdsa ";
        return  sql1;
    }

    /**
     * 拼装分页sql
     * @param sql
     * @param page
     * @param pagesize
     * @return
     */
    private String getPageSql(String sql,int page, int pagesize){
        page = (page - 1) * pagesize;// 获取到其实的显示数量
        String sql1 = "select * from ( ";
        sql1 += sql;
        sql1 += " ) sdasgasgasdsa limit " + page + "," + pagesize;
        return  sql1;
    }



    private void close(ResultSet rs, PreparedStatement ps, Connection con ) {
        try {
            if (rs != null) {
                rs.close();
                rs = null;
            }
            if (ps != null) {
                ps.close();
                ps = null;
            }
            if (con != null) {
                con.close();
                con = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
