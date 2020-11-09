package com.tinyplan.exam.common.config;

import com.tinyplan.exam.common.properties.DBProperties;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * 数据库配置类
 */
@Configuration
public class DBConfig {

    @Autowired
    private DBProperties dbProperties;

    /**
     * 数据源
     */
    @Bean
    public DataSource dataSource(){
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(dbProperties.getDriver());
        dataSource.setUrl(dbProperties.getUrl());
        dataSource.setUsername(dbProperties.getUsername());
        dataSource.setPassword(dbProperties.getPassword());
        dataSource.setMaxTotal(dbProperties.getMaxTotal());
        dataSource.setMaxIdle(dbProperties.getMaxIdle());
        dataSource.setInitialSize(dbProperties.getInitialSize());
        return dataSource;
    }

    /**
     * 事务管理器
     *
     * @param dataSource 数据源
     */
    @Bean
    public DataSourceTransactionManager transactionManager(DataSource dataSource){
        return new DataSourceTransactionManager(dataSource);
    }

}
