package com.hzz.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
@ConditionalOnClass({JedisPool.class})
@EnableConfigurationProperties({RedisConfig.class})
public class RedisAutoConfiguration {
    private static final Logger log = LoggerFactory.getLogger(RedisAutoConfiguration.class);

    public RedisAutoConfiguration() {
    }

    @Bean
    public JedisPool createJedisPool(RedisConfig rc) {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(rc.getMaxTotal());
        config.setMaxIdle(rc.getMaxIdle());
        config.setMinIdle(rc.getMinIdle());
        config.setMaxWaitMillis(rc.getMaxWaitMillis());
        config.setTestOnBorrow(rc.isTestOnBorrow());
        config.setTestOnReturn(rc.isTestOnReturn());
        config.setTestWhileIdle(rc.isTestWhileIdle());
        log.debug("初始化Redis! host={}; port={}", rc.getHost(), rc.getPort());
        JedisPool pool = new JedisPool(config, rc.getHost(), rc.getPort(), rc.getTimeout(), rc.getPassword(), rc.getSelect());
        return pool;
    }

    @Bean
    public RedisUtil createRedisUtil(JedisPool pool) {
        return new RedisUtil(pool);
    }
}

