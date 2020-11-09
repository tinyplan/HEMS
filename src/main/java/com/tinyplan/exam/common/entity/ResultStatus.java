package com.tinyplan.exam.common.entity;

/**
 * 状态码
 */
public enum ResultStatus {
    RES_SUCCESS(20000, "请求成功"),
    RES_ILLEGAL_TOKEN(50008, "非法token"),
    // TODO 更多的异常码

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
