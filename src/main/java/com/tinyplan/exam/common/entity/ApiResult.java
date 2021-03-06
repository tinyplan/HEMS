package com.tinyplan.exam.common.entity;

/**
 * 统一请求返回体
 *
 * @param <T> 数据体类型
 */
public class ApiResult<T> {
    private int code;
    private String message;
    private T data;

    public ApiResult(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public ApiResult(ResultStatus resultStatus, T data) {
        this(resultStatus.getCode(), resultStatus.getMessage(), data);
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }
}
