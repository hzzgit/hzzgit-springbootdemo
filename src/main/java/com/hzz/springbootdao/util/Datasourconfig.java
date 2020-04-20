package com.hzz.springbootdao.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @author hzz
 * @version 1.0
 * @date 2020/3/13 11:14
 */
@Component
//@EnableConfigurationProperties(DataSourcePropertiestest.class)
public class Datasourconfig {

    @Bean
    public DataSourcePropertiestest  tes1( DataSourcePropertiestest dataSourcePropertiestest){
        return  dataSourcePropertiestest;
    }
}
