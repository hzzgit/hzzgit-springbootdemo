package com.hzz.srpingbootdemo;

import com.github.pagehelper.PageHelper;
import com.hzz.springbootdao.Hzzdao;
import com.hzz.springbootdao.Mysqldb;
import com.hzz.springbootdao.util.Mysqldb2;
import net.fxft.ascsgpsproc.api.GpsInfoQueryHelper;
import net.fxft.ascsgpsproc.api.GpsInfoServiceImpl;
import net.fxft.ascsgpsproc.partition.PartitionTableManager;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.sql.DataSource;
import java.util.Properties;

@SpringBootApplication
@EnableScheduling
@MapperScan("com.hzz.dao")
@ComponentScan(basePackages = {"com.hzz"})
public class SrpingbootdemoApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(SrpingbootdemoApplication.class, args);
        System.out.println(context);
    }


    @Qualifier("dataSource")
    @Autowired
    private DataSource dataSource;

    @Bean
    @Qualifier("hzzdao1")
    public Hzzdao getmysqldb() {
        Hzzdao hzzdao = new Mysqldb();
        ((Mysqldb) hzzdao).setDataSource(dataSource);
        return hzzdao;
    }

    @Bean
    @Qualifier("hzzdao2")
    public Hzzdao getmysqldb2() {
        Hzzdao hzzdao = new Mysqldb2();
        ((Mysqldb2) hzzdao).setDataSource(dataSource);
        return hzzdao;
    }

    /**
     * 注入gps轨迹分表的类
     *
     * @return
     */
    @Bean
    public GpsInfoQueryHelper createGpsInfoQueryHelper() {
        return new GpsInfoQueryHelper();
    }

    /**
     * 将分表的配置类注入
     *
     * @return
     */
    @Bean
    public PartitionTableManager createPartitionTableManager() {
        return new PartitionTableManager();
    }

    /**
     * 将分表的历史轨迹服务注入
     *
     * @return
     */
    @Bean
    public GpsInfoServiceImpl createGpsinfo() {
        return new GpsInfoServiceImpl();
    }


}
