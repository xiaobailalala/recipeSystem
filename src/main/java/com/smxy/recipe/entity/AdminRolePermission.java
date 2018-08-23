/**
 * Copyright © 2018 eSunny Info. Developer Stu. All rights reserved.
 *
 * @Package:
 * @author: zpx
 * Build File @date: 2018/8/7 12:21
 * @Description TODO
 * @version 1.0
 */
package com.smxy.recipe.entity;

import java.io.Serializable;

public class AdminRolePermission implements Serializable {
    private Integer fId;
    private Integer fRid;
    private AdminRole adminRole;
    private Integer fPid;
    private AdminPermission adminPermission;

    public AdminRolePermission() {
    }

    public AdminRolePermission(Integer fId, Integer fRid, AdminRole adminRole, Integer fPid, AdminPermission adminPermission) {
        this.fId = fId;
        this.fRid = fRid;
        this.adminRole = adminRole;
        this.fPid = fPid;
        this.adminPermission = adminPermission;
    }

    public Integer getfId() {
        return fId;
    }

    public void setfId(Integer fId) {
        this.fId = fId;
    }

    public Integer getfRid() {
        return fRid;
    }

    public void setfRid(Integer fRid) {
        this.fRid = fRid;
    }

    public AdminRole getAdminRole() {
        return adminRole;
    }

    public void setAdminRole(AdminRole adminRole) {
        this.adminRole = adminRole;
    }

    public Integer getfPid() {
        return fPid;
    }

    public void setfPid(Integer fPid) {
        this.fPid = fPid;
    }

    public AdminPermission getAdminPermission() {
        return adminPermission;
    }

    public void setAdminPermission(AdminPermission adminPermission) {
        this.adminPermission = adminPermission;
    }

    @Override
    public String toString() {
        return "AdminRolePermission{" +
                "fId=" + fId +
                ", fRid=" + fRid +
                ", adminRole=" + adminRole +
                ", fPid=" + fPid +
                ", adminPermission=" + adminPermission +
                '}';
    }
}
