package com.tinyplan.exam.user.entity.vo;

import com.tinyplan.exam.user.entity.po.Role;
import com.tinyplan.exam.user.entity.po.UserDetail;

import java.io.Serializable;
import java.util.List;

/**
 * 用户信息整合 视图对象
 */
public class UserInfoVO implements Serializable {
    private String userId;
    private String accountName;
    private List<Role> roles;
    private UserDetail detail;

    public UserInfoVO() {}

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public UserDetail getDetail() {
        return detail;
    }

    public void setDetail(UserDetail detail) {
        this.detail = detail;
    }
}
