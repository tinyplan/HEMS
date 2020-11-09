package com.tinyplan.exam.common.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * JDBC 数据源配置参数
 */
@Component
@PropertySource("classpath:config/db.properties")
public class DBProperties {
    @Value("${jdbc.driver}")
    private String driver;
    @Value("${jdbc.url}")
    private String url;
    @Value("${jdbc.username}")
    private String username;
    @Value("${jdbc.password}")
    private String password;
    @Value("${jdbc.maxTotal}")
    private Integer maxTotal;
    @Value("${jdbc.maxIdle}")
    private Integer maxIdle;
    @Value("${jdbc.initialSize}")
    private Integer initialSize;

    public String getDriver() {
        return driver;
    }

    public String getUrl() {
        return url;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Integer getMaxTotal() {
        return maxTotal;
    }

    public Integer getMaxIdle() {
        return maxIdle;
    }

    public Integer getInitialSize() {
        return initialSize;
    }
}
