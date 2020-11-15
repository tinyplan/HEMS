package com.tinyplan.exam.common.entity;

/**
 * 状态码
 */
public enum ResultStatus {
    RES_SUCCESS(20000, "请求成功"),
    // 登录
    RES_LOGIN_UNKNOWN_USER(20001, "用户不存在"),
    RES_LOGIN_WRONG_PASS(20002, "密码错误"),
    RES_LOGIN_SUCCESS(20003, "登录成功"),
    // 获取用户信息
    RES_INFO_NOT_EXIST(20006, "用户信息不存在"),
    // 注册
    RES_EXIST_SAME_USER(20010, "存在重复用户"),

    RES_ILLEGAL_REQUEST(40003, "非法请求"),
    RES_UNKNOWN_ERROR(50000, "未知异常");

    private int code;
    private String message;

    ResultStatus(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
