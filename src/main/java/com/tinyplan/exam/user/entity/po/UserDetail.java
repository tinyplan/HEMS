package com.tinyplan.exam.user.entity.po;

import java.io.Serializable;

/**
 * 用户详细信息 持久化类
 */
public class UserDetail implements Serializable {
    // 用户ID
    private String userId;
    // 真实姓名
    private String realName;
    // 联系方式
    private String contact;
    // 生日
    private String birth;
    // 邮箱
    private String email;
    // 头像地址
    private String avatar;

    public UserDetail() {}

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getConcat() {
        return contact;
    }

    public void setConcat(String concat) {
        this.contact = concat;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
