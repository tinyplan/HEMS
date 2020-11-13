package com.tinyplan.exam.user.entity.vo;

public class TokenVO {
    private String token;

    public TokenVO() {}

    public TokenVO(String token){
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
