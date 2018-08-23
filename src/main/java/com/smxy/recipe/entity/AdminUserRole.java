/**
 * Copyright © 2018 eSunny Info. Developer Stu. All rights reserved.
 *
 * @Package:
 * @author: zpx
 * Build File @date: 2018/8/7 12:16
 * @Description TODO
 * @version 1.0
 */
package com.smxy.recipe.entity;

import java.io.Serializable;

public class AdminUserRole implements Serializable {
    private Integer fId;
    private Integer fUid;
    private AdminUser adminUser;
    private Integer fRid;
    private AdminRole adminRole;

    public AdminUserRole() {
    }

    public AdminUserRole(Integer fId, Integer fUid, AdminUser adminUser, Integer fRid, AdminRole adminRole) {
        this.fId = fId;
        this.fUid = fUid;
        this.adminUser = adminUser;
        this.fRid = fRid;
        this.adminRole = adminRole;
    }

    public Integer getfId() {
        return fId;
    }

    public void setfId(Integer fId) {
        this.fId = fId;
    }

    public Integer getfUid() {
        return fUid;
    }

    public void setfUid(Integer fUid) {
        this.fUid = fUid;
    }

    public AdminUser getAdminUser() {
        return adminUser;
    }

    public void setAdminUser(AdminUser adminUser) {
        this.adminUser = adminUser;
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

    @Override
    public String toString() {
        return "AdminUserRole{" +
                "fId=" + fId +
                ", fUid=" + fUid +
                ", adminUser=" + adminUser +
                ", fRid=" + fRid +
                ", adminRole=" + adminRole +
                '}';
    }
}
