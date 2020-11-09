package com.tinyplan.exam.common.config;

import com.tinyplan.exam.common.properties.RedisProperties;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettucePoolingClientConfiguration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.sql.Connection;

/**
 * Redis 配置
 */
@Configuration
public class RedisConfig {

    @Autowired
    private RedisProperties redisProperties;

    /**
     * 连接池参数
     *
     * @return
     */
    @Bean
    public GenericObjectPoolConfig<Connection> poolConfig() {
        GenericObjectPoolConfig<Connection> poolConfig = new GenericObjectPoolConfig<>();
        poolConfig.setMaxIdle(redisProperties.getMaxIdle());
        // genericObjectPoolConfig.setMaxTotal(maxTotal);
        // genericObjectPoolConfig.setMinIdle(minIdle);
        poolConfig.setMaxWaitMillis(redisProperties.getMaxWait());
        //在borrow一个实例时，是否提前进行validate操作；如果为true，则得到的实例均是可用的
        poolConfig.setTestOnBorrow(redisProperties.getTestOnBorrow());
        // 在空闲时检查有效性, 默认false
        // genericObjectPoolConfig.setTestWhileIdle(isTestWhileIdle);
        return poolConfig;
    }

    /**
     * 连接池代理配置
     *
     * @param poolConfig 连接池参数
     * @return
     */
    @Bean
    public LettuceClientConfiguration lettuceClientConfiguration(GenericObjectPoolConfig<Connection> poolConfig){
        return LettucePoolingClientConfiguration.builder()
                .poolConfig(poolConfig)
                .build();
    }

    /**
     * Redis 单机配置
     *
     * @return
     */
    @Bean
    public RedisStandaloneConfiguration standaloneConfiguration(){
        RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration();
        configuration.setHostName(redisProperties.getHost());
        configuration.setPort(redisProperties.getPort());
        configuration.setPassword(RedisPassword.of(redisProperties.getPassword()));
        configuration.setDatabase(redisProperties.getDbIndex());
        return configuration;
    }

    /**
     * 连接工厂
     *
     * @param configuration 单机配置
     * @param poolConfig 连接池配置
     * @return
     */
    @Bean
    public LettuceConnectionFactory lettuceConnectionFactory(RedisStandaloneConfiguration configuration, LettuceClientConfiguration poolConfig){
        LettuceConnectionFactory factory = new LettuceConnectionFactory(configuration, poolConfig);
        //校验连接是否有效
        factory.setValidateConnection(true);
        factory.afterPropertiesSet();
        return factory;
    }


    @Bean
    public StringRedisTemplate stringRedisTemplate(LettuceConnectionFactory factory){
        return new StringRedisTemplate(factory);
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate(LettuceConnectionFactory factory){
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(factory);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
        redisTemplate.setEnableTransactionSupport(true);
        return redisTemplate;
    }

}
