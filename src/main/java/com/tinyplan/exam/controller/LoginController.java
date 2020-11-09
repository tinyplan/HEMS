package com.tinyplan.exam.controller;

import com.tinyplan.exam.common.annotation.SysLog;
import com.tinyplan.exam.common.entity.ApiResult;
import com.tinyplan.exam.common.entity.BusinessException;
import com.tinyplan.exam.common.entity.ResultStatus;
import com.tinyplan.exam.dao.TestMapper;
import com.tinyplan.exam.entity.po.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RequestMapping("/login")
@RestController
public class LoginController {
    private final TestMapper testMapper;
    private final StringRedisTemplate stringRedisTemplate;

    @Autowired
    public LoginController(TestMapper testMapper,
                           StringRedisTemplate stringRedisTemplate) {
        this.testMapper = testMapper;
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @GetMapping("/test")
    @SysLog(module = "测试模块", method = "test()")
    public ApiResult<List<User>> test() {
        // stringRedisTemplate.opsForValue().set("status", "15132121");
        // stringRedisTemplate.delete("mike");
        return new ApiResult<>(ResultStatus.RES_SUCCESS, testMapper.getUser());
    }

    @GetMapping("/tokenTest")
    public ApiResult<String> tokenTest(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token == null || "".equals(token)) {
            token = request.getHeader("x-token");
            if (token == null || "".equals(token)) {
                throw new BusinessException(ResultStatus.RES_ILLEGAL_TOKEN);
            } else {
                return new ApiResult<>(ResultStatus.RES_SUCCESS, token);
            }
        } else {
            return new ApiResult<>(ResultStatus.RES_SUCCESS, token);
        }

    }

}

