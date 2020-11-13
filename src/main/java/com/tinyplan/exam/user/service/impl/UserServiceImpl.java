package com.tinyplan.exam.user.service.impl;

import cn.hutool.crypto.digest.DigestUtil;
import com.auth0.jwt.interfaces.Claim;
import com.tinyplan.exam.common.entity.BusinessException;
import com.tinyplan.exam.common.entity.ResultStatus;
import com.tinyplan.exam.common.service.TokenService;
import com.tinyplan.exam.common.utils.JWTUtil;
import com.tinyplan.exam.user.dao.RoleMapper;
import com.tinyplan.exam.user.dao.UserMapper;
import com.tinyplan.exam.user.entity.po.User;
import com.tinyplan.exam.user.entity.po.UserDetail;
import com.tinyplan.exam.user.entity.vo.UserInfoVO;
import com.tinyplan.exam.user.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Resource(name = "tokenServiceImpl")
    private TokenService tokenService;
    @Resource(name = "userMapper")
    private UserMapper userMapper;
    @Resource(name = "roleMapper")
    private RoleMapper roleMapper;

    /**
     * 用户登录
     *
     * @param username 账户名
     * @param password 密码
     * @return token令牌
     */
    @Override
    public String login(String username, String password) throws UnsupportedEncodingException {
        User user = userMapper.getUserByUsername(username);
        String token = null;
        if (user != null){
            token = "";
            if (user.getPassword().equalsIgnoreCase(DigestUtil.md5Hex(password))) {
                token = tokenService.generateToken(user.getId());
                // 将用户的信息直接取出, 并存放在缓存中
                UserInfoVO userInfo = this.getUserInfoByUserId(user.getId());
                tokenService.setValueByToken(token, userInfo);
            }
        }
        return token;
    }

    /**
     * 通过用户ID, 获取个人信息
     *
     * @param userId 用户ID
     * @return 个人信息
     */
    @Override
    public UserInfoVO getUserInfoByUserId(String userId) {
        User user = userMapper.getUserByUsername(userId);
        if (user == null) {
            throw new BusinessException(ResultStatus.RES_LOGIN_UNKNOWN_USER);
        }
        UserInfoVO userInfo = new UserInfoVO();
        userInfo.setUserId(user.getId());
        userInfo.setAccountName(user.getAccountName());
        List<String> roleIdList = new ArrayList<>();
        roleIdList.add(user.getRoleId());
        userInfo.setRoles(roleMapper.getRoles(roleIdList));
        userInfo.setDetail(userMapper.getUserDetailByUserId(user.getId()));
        return userInfo;
    }

    /**
     * 获取个人信息
     *
     * @param token 用户令牌
     * @return 个人信息
     */
    @Override
    public UserInfoVO getUserInfoByToken(String token) throws UnsupportedEncodingException {
        // 首先从缓存中查找信息
        UserInfoVO userInfo = (UserInfoVO) tokenService.getValueByToken(token);
        // 查询失败, 查找数据库
        if (userInfo == null) {
            // 先取出token中的userId
            Map<String, Claim> claims = JWTUtil.verify(token);
            String userId = claims.get("userId").asString();
            // 查询数据库
            userInfo = this.getUserInfoByUserId(userId);
        }
        return userInfo;
    }

    @Override
    public void logout(String token) {
        // 删除token的时候, 失败了也没有问题(大概), 登录的时候重新生成一个就好了
        tokenService.removeValueByToken(token);
    }
}
