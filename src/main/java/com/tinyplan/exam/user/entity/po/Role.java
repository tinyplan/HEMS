package com.tinyplan.exam.user.entity.po;

import java.io.Serializable;

/**
 * 用户角色 持久化类
 */
public class Role implements Serializable {
    // 角色ID
    private String roleId;
    // 角色名称
    private String roleName;
    // 角色描述
    private String description;

    public Role() {}

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
