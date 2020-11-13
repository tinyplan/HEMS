package com.tinyplan.exam.user.controller;

import com.tinyplan.exam.common.annotation.Authorization;
import com.tinyplan.exam.common.entity.ApiResult;
import com.tinyplan.exam.common.entity.BusinessException;
import com.tinyplan.exam.common.entity.ResultStatus;
import com.tinyplan.exam.common.utils.TokenUtil;
import com.tinyplan.exam.user.entity.query.LoginForm;
import com.tinyplan.exam.user.entity.vo.TokenVO;
import com.tinyplan.exam.user.entity.vo.UserInfoVO;
import com.tinyplan.exam.user.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

/**
 * 门户、后台的用户共用一个接口
 */
@RestController
public class UserController {
    @Resource(name = "userServiceImpl")
    private UserService userService;

    @PostMapping("/system/user/login")
    public ApiResult<TokenVO> login(@RequestBody LoginForm loginForm) throws UnsupportedEncodingException {
        // TODO 登录参数校验
        String token = userService.login(loginForm.getUsername(), loginForm.getPassword());
        if (token == null) {
            throw new BusinessException(ResultStatus.RES_LOGIN_UNKNOWN_USER);
        } else if ("".equals(token)) {
            throw new BusinessException(ResultStatus.RES_LOGIN_WRONG_PASS);
        }
        return new ApiResult<>(ResultStatus.RES_LOGIN_SUCCESS, new TokenVO(token));
    }

    @GetMapping("/system/user/info")
    @Authorization
    public ApiResult<UserInfoVO> getUserInfo(HttpServletRequest request) throws UnsupportedEncodingException {
        String token = TokenUtil.getToken(request);
        UserInfoVO userInfo = userService.getUserInfoByToken(token);
        // 这里再找不到的话, 则用户不存在(其实不太可能发生这种情况)
        if (userInfo == null) {
            throw new BusinessException(ResultStatus.RES_INFO_NOT_EXIST);
        }
        return new ApiResult<>(ResultStatus.RES_SUCCESS, userInfo);
    }

    @PostMapping("/system/user/logout")
    @Authorization
    public ApiResult<Object> logout(HttpServletRequest request){
        String token = TokenUtil.getToken(request);
        // 一般不会出现token为null的情况, 因为前面的拦截器已经检查过
        // token 若为 null, 说明运行到这里的时候刚好失效(老非酋了), 那就不用去删了, token已经自我消亡
        if (token == null) {
            throw new BusinessException(ResultStatus.RES_ILLEGAL_REQUEST);
        }
        userService.logout(token);
        return new ApiResult<>(ResultStatus.RES_SUCCESS, null);
    }

}
