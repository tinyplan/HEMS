package com.tinyplan.exam.common.service.impl;

import com.tinyplan.exam.common.entity.Log;
import com.tinyplan.exam.common.service.LogService;
import org.springframework.stereotype.Service;

@Service
public class LogServiceImpl implements LogService {

    @Override
    public Integer insertLog(Log log) {
        // TODO 向数据库内插入日志数据
        System.out.println(log);
        return 1;
    }
}
