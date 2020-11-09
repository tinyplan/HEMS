package com.tinyplan.exam.common.service.impl;

import com.tinyplan.exam.common.properties.HEMSProperties;
import com.tinyplan.exam.common.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class TokenServiceImpl implements TokenService {
    private final RedisTemplate<String, Object> redisTemplate;
    private final HEMSProperties hemsProperties;

    @Autowired
    public TokenServiceImpl(RedisTemplate<String, Object> redisTemplate, HEMSProperties hemsProperties){
        this.redisTemplate = redisTemplate;
        this.hemsProperties = hemsProperties;
    }

    /**
     * 获取token对应的值
     * @param key token
     * @return  对应的值
     */
    public Object getToken(String key){
        return redisTemplate.opsForValue().get(key);
    }

    public void setToken(String key, Object value){
        this.setToken(key, value, hemsProperties.getTokenExpire());
    }

    /**
     * 设置token
     * @param key       token
     * @param value     值
     * @param expire    有效时间(单位: 分钟)
     */
    private void setToken(String key, Object value, int expire){
        redisTemplate.execute(new SessionCallback<Object>() {
            @Override
            public Object execute(RedisOperations redisOperations) throws DataAccessException {
                redisTemplate.opsForValue().set(key, value, expire, TimeUnit.MINUTES);
                return null;
            }
        });
    }

    /**
     * 移除token
     * @param key token
     */
    public void removeToken(String key){
        redisTemplate.execute(new SessionCallback<Object>() {
            @Override
            public Object execute(RedisOperations redisOperations) throws DataAccessException {
                redisTemplate.delete(key);
                return null;
            }
        });
    }

    /**
     * 生成token
     * @param isEncrypt 是否需要加密
     * @param bases 基础字段
     * @return token
     */
    // TODO 使用JWT
    public String generateToken(boolean isEncrypt, String... bases) {
        return "";
    }

    /**
     * 检查token是否存在
     * @param key token
     * @return 是否存在
     */
    public boolean checkToken(String key){
        return this.getToken(key) != null;
    }

    /**
     * 刷新有效时间
     * @param key token
     * @param expire 有效时间
     */
    public void flushExpire(String key, int expire){
        redisTemplate.execute(new SessionCallback<Object>() {
            @Override
            public Object execute(RedisOperations redisOperations) throws DataAccessException {
                redisTemplate.expire(key, expire, TimeUnit.MINUTES);
                return null;
            }
        });
    }
}
