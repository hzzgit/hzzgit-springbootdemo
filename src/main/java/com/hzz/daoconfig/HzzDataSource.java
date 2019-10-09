package com.hzz.daoconfig;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.util.Properties;

public class HzzDataSource {
  private static HzzDataSource config;
  public static DataSource dataSource;
  public static final String ORACLE = "oracleDataSource.properties";
  public static final String MYSQL = "DataSource.properties";

  public static final String web = "1";
  public static final String jdk = "2";


  private HzzDataSource(String TYPE, String from) {
    createdateSource(TYPE, from);
  }

  public static void createhzzdb(String TYPE, String from) {
    // TODO Auto-generated constructor stub
    if (config == null) {
      config = new HzzDataSource(TYPE, from);
    }
  }

  // 创建连接池
  public void createdateSource(String type, String from) {

    Properties properties = new Properties();
    try {
      // properties.load(config.class.getResourceAsStream("/oracleDataSource.properties"));//这个是读取linux的class路径
      // properties.load(config.class.getResourceAsStream("/oracleDataSource.properties"));//这是读取src路径下
      if (HzzDataSource.web.equals(from)) {
        String realPath = this.getClass().getClassLoader().getResource(type).getFile();
        properties.load(new FileInputStream(realPath));// 这个是web项目用的
      } else if (HzzDataSource.jdk.equals(from)) {
        properties.load(new FileInputStream(type));// 这是直接读取项目下面的
      }
      config.dataSource = DruidDataSourceFactory.createDataSource(properties);
      Connection connection = dataSource.getConnection();
      if (connection != null) {
        System.out.println("数据库连接成功 success");
      } else {
        System.out.println("con fail");

      }
    } catch (FileNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

}
