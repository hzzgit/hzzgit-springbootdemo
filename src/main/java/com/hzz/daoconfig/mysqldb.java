package com.hzz.daoconfig;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/**
 * 进行gbase,mysql数据库查询
 */
public class mysqldb implements hzzdao {
    private PreparedStatement ps;
    private ResultSet rs;
    private Statement st;
    private Connection con = null;


    /**
     * 根据类进行分页查询
     * @param sql
     * @param object2
     * @param page
     * @param pagesize
     * @param wdata
     * @return
     */
    public Object searchpagesqlclass(String sql, Class object2, int page, int pagesize,
                                     Object... wdata) {
        String sqlpage = getpagesql(sql, page, pagesize);
        return  searchnopagesqlclass(sqlpage,object2,wdata);
    }



    /*
     * (non-Javadoc)
     *
     * @see daoconfig.mysqldao#close()
     */
    private void close() {
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




    private String getpagesql(String sql,int page, int pagesize){
        page = (page - 1) * pagesize;// 获取到其实的显示数量
        String sql1 = "select * from ( ";
        sql1 += sql;
        sql1 += " ) sdasgasgasdsa limit " + page + "," + pagesize;
        return  sql1;
    }


    public  ArrayList searchnopagesqlclass(String sql, Class object2, Object... wdata) {
        try {
            con = HzzDataSource.dataSource.getConnection();
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
        }

        this.close();
        // System.out.print(cols.toString());
        return jsonArray;
    }

  /*
   * (non-Javadoc)
   * 
   * @see daoconfig.mysqldao#searchpagesqlclass(java.lang.String, java.lang.Object, int, int,
   * java.lang.Object)
   */

    public Object searchpagesqlclass(String sql, Object object2, int page, int pagesize,
                                     Object... wdata) {

        try {
            con = HzzDataSource.dataSource.getConnection();
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        if (con != null) {
        } else {
        }

        ArrayList jsonArray = new ArrayList();
        try {
            page = (page - 1) * pagesize;// 获取到其实的显示数量
            String sql1 = "select * from ( ";
            sql1 += sql;
            sql1 += " ) sdasgasgasdsa limit " + page + "," + pagesize;
            ps = con.prepareStatement(sql1);
            for (int i = 0; i < wdata.length; i++) {
                ps.setObject(i + 1, wdata[i]);
            }
            rs = ps.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            int colnum = rsmd.getColumnCount();
            Class<? extends Object> c2 = object2.getClass();
            while (rs.next()) {
                Object object = c2.newInstance();// 创建新的对象
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
//                        methods2 = c.getMethod("set" + filename, int.class);// 注意参数不是String,是string
//                        // 类型
//                        int colval = rs.getInt(i);
//                        methods2.invoke(object, colval);// 通过对象，调用有参数的方法
                    }

                }
                jsonArray.add(object);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        this.close();
        // System.out.print(cols.toString());

        return jsonArray;
    }

    /*
     * (non-Javadoc)
     *
     * @see daoconfig.mysqldao#searchnopagesqljson(java.lang.String, java.lang.Object)
     */
    public String searchnopagesqljson(String sql, Object... wdata) {

        String text = "";// 最终要返回的json格式字符串
        try {
            con = HzzDataSource.dataSource.getConnection();
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        if (con != null) {
        } else {
        }

        JSONArray jsonArray = new JSONArray();
        try {

            ps = con.prepareStatement(sql);
            for (int i = 0; i < wdata.length; i++) {
                ps.setObject(i + 1, wdata[i]);
            }
            rs = ps.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            int colnum = rsmd.getColumnCount();
            while (rs.next()) {
                JSONObject jObject = new JSONObject();
                for (int i = 1; i <= colnum; i++) {
                    String colname = rsmd.getColumnName(i);
                    String colval = rs.getString(i);

                    jObject.put(colname, colval);
                }
                jsonArray.add(jObject);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        this.close();
        // System.out.print(cols.toString());
        text = jsonArray.toJSONString();
        return text;

    }

    /*
     * (non-Javadoc)
     *
     * @see daoconfig.mysqldao#searchnopagesqlobject(java.lang.String, java.lang.Object)
     */
    public Object searchnopagesqlobject(String sql, Object... wdata) {

        String text = "";// 最终要返回的json格式字符串
        try {
            con = HzzDataSource.dataSource.getConnection();
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        if (con != null) {
        } else {
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
            while (rs.next()) {
                Map jObject = new HashMap();
                for (int i = 1; i <= colnum; i++) {
                    String colname = rsmd.getColumnName(i);
                    String colval = rs.getString(i);

                    jObject.put(colname, colval);
                }
                jsonArray.add(jObject);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        this.close();
        // System.out.print(cols.toString());

        return jsonArray;
    }

  /*
   * (non-Javadoc)
   * 
   * @see daoconfig.mysqldao#searchpagesqlobject(java.lang.String, int, int, java.lang.Object)
   */

    public Object searchpagesqlobject(String sql, int page, int pagesize, Object... wdata) {

        String text = "";// 最终要返回的json格式字符串
        try {
            con = HzzDataSource.dataSource.getConnection();
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        if (con != null) {
        } else {
        }

        ArrayList jsonArray = new ArrayList();
        try {

            page = (page - 1) * pagesize;// 获取到其实的显示数量
            String sql1 = "select * from ( ";
            sql1 += sql;
            sql1 += " ) sdasgasgasdsa limit " + page + "," + pagesize;

            ps = con.prepareStatement(sql1);
            for (int i = 0; i < wdata.length; i++) {
                ps.setObject(i + 1, wdata[i]);
            }
            rs = ps.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            int colnum = rsmd.getColumnCount();
            while (rs.next()) {
                Map jObject = new HashMap();
                for (int i = 1; i <= colnum; i++) {
                    String colname = rsmd.getColumnName(i);
                    String colval = rs.getString(i);
                    jObject.put(colname, colval);
                }
                jsonArray.add(jObject);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        this.close();
        // System.out.print(cols.toString());

        return jsonArray;
    }

  /*
   * (non-Javadoc)
   * 
   * @see daoconfig.mysqldao#searchpagesqljson(java.lang.String, int, int, java.lang.Object)
   */

    public String searchpagesqljson(String sql, int page, int pagesize, Object... wdata) {

        String text = "";// 最终要返回的json格式字符串
        try {
            con = HzzDataSource.dataSource.getConnection();
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        if (con != null) {
        } else {
        }

        JSONArray jsonArray = new JSONArray();
        try {

            page = (page - 1) * pagesize;// 获取到其实的显示数量
            String sql1 = "select * from ( ";
            sql1 += sql;
            sql1 += " ) sdasgasgasdsa limit " + page + "," + pagesize;

            ps = con.prepareStatement(sql1);
            for (int i = 0; i < wdata.length; i++) {
                ps.setObject(i + 1, wdata[i]);
            }
            rs = ps.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            int colnum = rsmd.getColumnCount();
            while (rs.next()) {
                JSONObject jObject = new JSONObject();
                for (int i = 1; i <= colnum; i++) {
                    String colname = rsmd.getColumnName(i);
                    String colval = rs.getString(i);
                    jObject.put(colname, colval);
                }
                jsonArray.add(jObject);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        this.close();
        // System.out.print(cols.toString());
        text = jsonArray.toJSONString();
        return text;
    }

  /*
   * (non-Javadoc)
   * 
   * @see daoconfig.mysqldao#executesql(java.util.ArrayList)
   */

    public boolean executesql(ArrayList listsql) {
        boolean arg = false;

        try {
            con = HzzDataSource.dataSource.getConnection();
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
        } finally {
            this.close();
        }
        return arg;
    }

    public String getnosqloneval(String sql, Object... wdata) {
        // TODO Auto-generated method stub
        return null;
    }

    public ArrayList<String> getnosqlallval(String sql, Object... wdata) {
        // TODO Auto-generated method stub
        return null;
    }

    public boolean executesql(String sql, Object... wdata) {
        // TODO Auto-generated method stub
        boolean arg = false;

        try {
            con = HzzDataSource.dataSource.getConnection();
            con.setAutoCommit(false);
            ps = con.prepareStatement(sql);
            for (int i = 0; i < wdata.length; i++) {
                ps.setObject(i + 1, wdata[i]);
            }
            arg = ps.execute();


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
        } finally {
            this.close();
        }
        return arg;
    }

    public Connection getConnection() {
        // TODO Auto-generated method stub
        return null;
    }

    public boolean noexecutesql(Connection con, String sql, Object... wdata) {
        // TODO Auto-generated method stub
        return false;
    }

    public boolean allexecute(Connection con) {
        // TODO Auto-generated method stub
        return false;
    }

    public boolean executealsql(ArrayList sql) {
        // TODO Auto-generated method stub
        return false;
    }

}
