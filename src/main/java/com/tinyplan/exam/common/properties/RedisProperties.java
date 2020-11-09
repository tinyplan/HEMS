package com.tinyplan.exam.common.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * Redis 配置参数
 */
@Component
@PropertySource("classpath:config/redis.properties")
public class RedisProperties {
    @Value("${redis.host}")
    private String host;
    @Value("${redis.port}")
    private Integer port;
    @Value("${redis.password}")
    private String password;
    @Value("${redis.dbIndex}")
    private Integer dbIndex;
    // 下面是连接池的配置
    @Value("${redis.pool.maxIdle}")
    private Integer maxIdle;
    @Value("${redis.pool.maxActive}")
    private Integer maxActive;
    @Value("${redis.pool.maxWait}")
    private Integer maxWait;
    @Value("${redis.pool.testOnBorrow}")
    private Boolean testOnBorrow;

    public String getHost() {
        return host;
    }

    public Integer getPort() {
        return port;
    }

    public String getPassword() {
        return password;
    }

    public Integer getMaxIdle() {
        return maxIdle;
    }

    public Integer getMaxActive() {
        return maxActive;
    }

    public Integer getMaxWait() {
        return maxWait;
    }

    public Boolean getTestOnBorrow() {
        return testOnBorrow;
    }

    public Integer getDbIndex() {
        return dbIndex;
    }
}
