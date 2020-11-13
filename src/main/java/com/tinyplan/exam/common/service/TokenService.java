package com.tinyplan.exam.common.service;

import java.io.UnsupportedEncodingException;

public interface TokenService {

    Object getValueByToken(String key);

    void setValueByToken(String key, Object value);

    void removeValueByToken(String key);

    String generateToken(String userId) throws UnsupportedEncodingException;

    boolean checkToken(String key);

    void flushExpire(String key, int expire);

}
