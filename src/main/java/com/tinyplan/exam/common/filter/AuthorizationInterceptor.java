package com.tinyplan.exam.common.filter;

import com.tinyplan.exam.common.annotation.Authorization;
import com.tinyplan.exam.common.entity.BusinessException;
import com.tinyplan.exam.common.entity.ResultStatus;
import com.tinyplan.exam.common.service.TokenService;
import org.aspectj.lang.annotation.Around;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * 授权拦截器：检查token是否合法
 */
public class AuthorizationInterceptor extends HandlerInterceptorAdapter {
    private final TokenService tokenService;

    @Autowired
    public AuthorizationInterceptor(TokenService tokenService){
        this.tokenService = tokenService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        // 映射到方法
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();
            // 检测方法是否被@Authorization注解
            if (method.getAnnotation(Authorization.class) != null) {
                String token = this.getToken(request);
                if (token == null || !tokenService.checkToken(token)) {
                    // 未通过验证
                    throw new BusinessException(ResultStatus.RES_ILLEGAL_TOKEN);
                } else {
                    // 刷新时间
                    tokenService.flushExpire(token, 30);
                }
            }
        }
        return true;
    }

    /**
     * 获取token
     * @param request 请求体
     * @return token
     */
    private String getToken(HttpServletRequest request) {
        // 从Header中获取x-token
        String token = request.getHeader("x-token");
        return token == null? request.getHeader("Authorization") : token;
    }
}
