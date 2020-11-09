package com.tinyplan.exam.common.service;

public interface TokenService {

    Object getToken(String key);

    void setToken(String key, Object value);

    void setToken(String key, Object value, int expire);

    void removeToken(String key);

    String generateToken(boolean isEncrypt, String... bases);

    boolean checkToken(String key);

    void flushExpire(String key, int expire);

}
