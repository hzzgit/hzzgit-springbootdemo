package com.hzz;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.hzz.springbootdao.Hzzdao;
import com.hzz.springbootdao.Mysqldb;
import com.hzz.springbootdao.util.Mysqldb2;
import com.hzz.注解的使用.ConditionalOnClass.Van;
import com.hzz.注解的使用.TraceLog;
import net.fxft.ascsgpsproc.api.GpsInfoQueryHelper;
import net.fxft.ascsgpsproc.api.GpsInfoServiceImpl;
import net.fxft.ascsgpsproc.partition.PartitionTableManager;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.util.ReflectionUtils;

import javax.sql.DataSource;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Optional;

@SpringBootApplication
@EnableScheduling
@MapperScan("com.hzz.dao")
@Order(1)
public class SrpingbootdemoApplication  implements CommandLineRunner {

    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(SrpingbootdemoApplication.class, args);
        //获取自定义注解的配置
        final Map<String, Object> beansWithAnnotation = applicationContext.getBeansWithAnnotation(TraceLog.class);
        for (String key : beansWithAnnotation.keySet()) {
            //Spring 代理类导致Method无法获取,这里使用AopUtils.getTargetClass()方法
            Method[] methods = ReflectionUtils.getAllDeclaredMethods(AopUtils.getTargetClass(beansWithAnnotation.get(key).getClass()));
            for (Method method : methods) {
                //获取指定方法上的注解的属性
                final TraceLog initRetryRabbitMq = AnnotationUtils.findAnnotation(method, TraceLog.class);
                if (null != initRetryRabbitMq) {
                    //验证必要的注解的属性
                    String queueName = Optional.ofNullable(initRetryRabbitMq.business()).orElseThrow(() -> new IllegalArgumentException("Please specify the queue name of the queue!"));

                    //多个bean的时候相当于起个别名
                    String registerBean = queueName + "InitConfigurerationBean";

                    //将bean注册到Spring容器中,通过构造函数的方式进行注入
                    //   SpringRegisterBean.registerBean((ConfigurableApplicationContext) applicationContext, registerBean, TraceLog.class, initRetryRabbitMq, applicationContext);

                }
            }
        }
    }

    @Autowired
    private Van van;


    @Override
    public void run(String... args) throws Exception {
        //do something
        van.fight();
    }

    /**
     * 分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
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
