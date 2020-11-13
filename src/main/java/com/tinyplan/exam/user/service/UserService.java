package com.tinyplan.exam.user.service;

import com.tinyplan.exam.user.entity.vo.UserInfoVO;

import java.io.UnsupportedEncodingException;

public interface UserService {

    String login(String username, String password) throws UnsupportedEncodingException;

    UserInfoVO getUserInfoByUserId(String userId);

    UserInfoVO getUserInfoByToken(String token) throws UnsupportedEncodingException;

    void logout(String token);

}
