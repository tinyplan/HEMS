package com.tinyplan.exam.user.entity.po;

/**
 * 用户基础信息 持久化类
 */
public class User {
    // 用户ID
    private String id;
    // 账户名
    private String accountName;
    // 密码
    private String password;
    // 角色ID
    private String roleId;

    public User() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
}
