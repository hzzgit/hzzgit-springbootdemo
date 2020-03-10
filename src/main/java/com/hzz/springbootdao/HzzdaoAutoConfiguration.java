package com.hzz.springbootdao;

import net.fxft.cloud.jdbc.SpringConnectionSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

@Configuration
@ConditionalOnClass({DataSource.class, EmbeddedDatabaseType.class})
@AutoConfigureAfter({DataSourceAutoConfiguration.class})
public class HzzdaoAutoConfiguration {
    private static final Logger log = LoggerFactory.getLogger(HzzdaoAutoConfiguration.class);


    public HzzdaoAutoConfiguration() {
    }


    @Bean
    @Qualifier("hzzdaonew")
    @ConditionalOnMissingBean({Hzzdao.class})
    public Hzzdao createhzzSpringJdbcUtil(@Qualifier("dataSource") DataSource dataSource) {
        Hzzdao ju = new Mysqldb(dataSource,new SpringConnectionhzzSource(dataSource));
        return  ju;
    }


}
