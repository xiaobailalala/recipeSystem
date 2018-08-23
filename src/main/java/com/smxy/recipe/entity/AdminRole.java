/**
 * Copyright © 2018 eSunny Info. Developer Stu. All rights reserved.
 *
 * @Package:
 * @author: zpx
 * Build File @date: 2018/8/5 22:35
 * @Description TODO
 * @version 1.0
 */
package com.smxy.recipe.entity;

import java.io.Serializable;
import java.util.List;

public class AdminRole implements Serializable {
    private Integer fId;
    private String fRolename;
    private String fIntroduction;
    private List<AdminRolePermission> adminRolePermissions;

    public AdminRole() {
    }

    public AdminRole(Integer fId, String fRolename, String fIntroduction, List<AdminRolePermission> adminRolePermissions) {
        this.fId = fId;
        this.fRolename = fRolename;
        this.fIntroduction = fIntroduction;
        this.adminRolePermissions = adminRolePermissions;
    }

    public Integer getfId() {
        return fId;
    }

    public void setfId(Integer fId) {
        this.fId = fId;
    }

    public String getfRolename() {
        return fRolename;
    }

    public void setfRolename(String fRolename) {
        this.fRolename = fRolename;
    }

    public String getfIntroduction() {
        return fIntroduction;
    }

    public void setfIntroduction(String fIntroduction) {
        this.fIntroduction = fIntroduction;
    }

    public List<AdminRolePermission> getAdminRolePermissions() {
        return adminRolePermissions;
    }

    public void setAdminRolePermissions(List<AdminRolePermission> adminRolePermissions) {
        this.adminRolePermissions = adminRolePermissions;
    }

    @Override
    public String toString() {
        return "AdminRole{" +
                "fId=" + fId +
                ", fRolename='" + fRolename + '\'' +
                ", fIntroduction='" + fIntroduction + '\'' +
                ", adminRolePermissions=" + adminRolePermissions +
                '}';
    }
}
