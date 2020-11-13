package com.tinyplan.exam.common.service.impl;

import cn.hutool.crypto.digest.DigestUtil;
import com.tinyplan.exam.common.entity.BusinessException;
import com.tinyplan.exam.common.properties.HEMSProperties;
import com.tinyplan.exam.common.service.TokenService;
import com.tinyplan.exam.common.utils.EncryptUtil;
import com.tinyplan.exam.common.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service("tokenServiceImpl")
public class TokenServiceImpl implements TokenService {
    @Resource(name = "redisTemplate")
    private RedisTemplate<String, Object> redisTemplate;
    @Resource(name = "hemsProperties")
    private HEMSProperties hemsProperties;

    /**
     * 生成token
     *
     * @param userId 用户ID
     * @return token(若选择加密, 则会使用AES对称加密方式)
     */
    public String generateToken(String userId) throws UnsupportedEncodingException {
        Map<String , String> map = new HashMap<>();
        map.put("userId", userId);
        String token = JWTUtil.sign(map, hemsProperties.getTokenExpire());
        if (hemsProperties.isEncrypt()) {
            token = EncryptUtil.encryptByAES(token);
        }
        return token;
    }

    /**
     * 检查token是否存在
     *
     * @param token token
     * @return 是否存在
     */
    public boolean checkToken(String token){
        return this.getValueByToken(token) != null;
    }

    /**
     * 获取token对应的值
     *
     * @param token token令牌
     * @return  对应的值
     */
    public Object getValueByToken(String token){
        return redisTemplate.opsForValue().get(token);
    }

    public void setValueByToken(String token, Object value){
        this.setValueByToken(token, value, hemsProperties.getTokenExpire());
    }

    /**
     * 设置token对应的值
     *
     * @param token       token
     * @param value     值
     * @param expire    有效时间(单位: 分钟)
     */
    private void setValueByToken(String token, Object value, int expire){
        redisTemplate.execute(new SessionCallback<Object>() {
            @Override
            public Object execute(RedisOperations redisOperations) throws DataAccessException {
                redisTemplate.opsForValue().set(token, value, expire, TimeUnit.MINUTES);
                return null;
            }
        });
    }

    /**
     * 移除token
     *
     * @param token token
     */
    public void removeValueByToken(String token){
        redisTemplate.execute(new SessionCallback<Object>() {
            @Override
            public Object execute(RedisOperations redisOperations) throws DataAccessException {
                redisTemplate.delete(token);
                return null;
            }
        });
    }

    /**
     * 刷新有效时间
     *
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
