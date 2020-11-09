package com.tinyplan.exam.common.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * 系统配置参数
 */
@Component
@PropertySource("classpath:config/hems.properties")
public class HEMSProperties {
    @Value("${hems.token.expire:3600}")
    private Integer tokenExpire;

    public Integer getTokenExpire() {
        return tokenExpire;
    }
}
