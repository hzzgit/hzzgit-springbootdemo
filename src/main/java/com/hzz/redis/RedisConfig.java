//package com.hzz.redis;
//
//import org.springframework.boot.context.properties.ConfigurationProperties;
//
//@ConfigurationProperties(
//        prefix = "fxft.redis"
//)
//public class RedisConfig {
//    private String host;
//    private int port;
//    private String password;
//    private int timeout;
//    private int maxTotal;
//    private int maxIdle;
//    private int minIdle;
//    private long maxWaitMillis;
//    private boolean testOnBorrow;
//    private boolean testOnReturn;
//    private boolean testWhileIdle;
//    private int select = 0;
//
//    public RedisConfig() {
//    }
//
//    public String getHost() {
//        return this.host;
//    }
//
//    public void setHost(String host) {
//        this.host = host;
//    }
//
//    public int getPort() {
//        return this.port;
//    }
//
//    public void setPort(int port) {
//        this.port = port;
//    }
//
//    public String getPassword() {
//        return this.password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public int getTimeout() {
//        return this.timeout;
//    }
//
//    public void setTimeout(int timeout) {
//        this.timeout = timeout;
//    }
//
//    public int getMaxTotal() {
//        return this.maxTotal;
//    }
//
//    public void setMaxTotal(int maxTotal) {
//        this.maxTotal = maxTotal;
//    }
//
//    public int getMaxIdle() {
//        return this.maxIdle;
//    }
//
//    public void setMaxIdle(int maxIdle) {
//        this.maxIdle = maxIdle;
//    }
//
//    public int getMinIdle() {
//        return this.minIdle;
//    }
//
//    public void setMinIdle(int minIdle) {
//        this.minIdle = minIdle;
//    }
//
//    public long getMaxWaitMillis() {
//        return this.maxWaitMillis;
//    }
//
//    public void setMaxWaitMillis(long maxWaitMillis) {
//        this.maxWaitMillis = maxWaitMillis;
//    }
//
//    public boolean isTestOnBorrow() {
//        return this.testOnBorrow;
//    }
//
//    public void setTestOnBorrow(boolean testOnBorrow) {
//        this.testOnBorrow = testOnBorrow;
//    }
//
//    public boolean isTestOnReturn() {
//        return this.testOnReturn;
//    }
//
//    public void setTestOnReturn(boolean testOnReturn) {
//        this.testOnReturn = testOnReturn;
//    }
//
//    public boolean isTestWhileIdle() {
//        return this.testWhileIdle;
//    }
//
//    public void setTestWhileIdle(boolean testWhileIdle) {
//        this.testWhileIdle = testWhileIdle;
//    }
//
//    public int getSelect() {
//        return this.select;
//    }
//
//    public void setSelect(int select) {
//        this.select = select;
//    }
//}
