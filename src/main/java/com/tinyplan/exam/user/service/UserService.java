package com.tinyplan.exam.user.service;

import com.tinyplan.exam.user.entity.po.User;
import com.tinyplan.exam.user.entity.po.UserDetail;
import com.tinyplan.exam.user.entity.vo.UserInfoVO;

import java.io.UnsupportedEncodingException;

public interface UserService {

    boolean existSameUser(String accountName);

    boolean register(User user, UserDetail userDetail);

    String login(String username, String password) throws UnsupportedEncodingException;

    UserInfoVO getUserInfoByUserId(String userId);

    UserInfoVO getUserInfoByToken(String token) throws UnsupportedEncodingException;

    void logout(String token);

}
