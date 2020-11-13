package com.tinyplan.exam;

import com.tinyplan.exam.common.annotation.SysLog;
import com.tinyplan.exam.common.entity.ApiResult;
import com.tinyplan.exam.common.entity.BusinessException;
import com.tinyplan.exam.common.entity.ResultStatus;
import com.tinyplan.exam.user.dao.UserMapper;
import com.tinyplan.exam.user.entity.po.User;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.List;

@RequestMapping("/test")
@RestController
public class TestController {
    @Resource
    private UserMapper userMapper;
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 测试redis
     */
    @GetMapping("/redisTest")
    @SysLog(module = "测试模块", method = "test()")
    public ApiResult<List<User>> testRedis() {
        stringRedisTemplate.opsForValue().set("status", "15132121");
        stringRedisTemplate.delete("mike");
        return new ApiResult<>(ResultStatus.RES_SUCCESS, userMapper.getUsers());
    }

    /**
     * 测试token
     */
    @GetMapping("/tokenTest")
    public ApiResult<String> testToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token == null || "".equals(token)) {
            token = request.getHeader("x-token");
            if (token == null || "".equals(token)) {
                throw new BusinessException(ResultStatus.RES_ILLEGAL_REQUEST);
            }
        }
        return new ApiResult<>(ResultStatus.RES_SUCCESS, token);
    }

    @PostMapping("/upload")
    public ApiResult<String> testUploadImage(HttpServletRequest request,
                                             @RequestParam("file") MultipartFile[] fileList) {
        File filePath = new File(request.getServletContext().getRealPath("upload/test"));
        if (!filePath.exists() && !filePath.mkdirs()) {
            throw new BusinessException(ResultStatus.RES_UNKNOWN_ERROR, "上传文件失败");
        }
        for (MultipartFile file : fileList) {
            System.out.println(file.getOriginalFilename());
            File currentFile = new File(filePath + File.separator + file.getOriginalFilename());
            try (BufferedInputStream in = new BufferedInputStream(file.getInputStream());
                 BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(currentFile))) {
                int b;
                while ((b = in.read()) != -1){
                    out.write(b);
                }
                out.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return new ApiResult<>(ResultStatus.RES_SUCCESS, "success");
    }

}
