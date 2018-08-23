/**
 * Copyright © 2018 eSunny Info. Developer Stu. All rights reserved.
 *
 * @Package:
 * @author: zpx
 * Build File @date: 2018/8/5 22:29
 * @Description TODO
 * @version 1.0
 */
package com.smxy.recipe.entity;

import java.io.Serializable;
import java.util.List;

public class AdminUser implements Serializable {
    private Integer fId;
    private String fAccount;
    private String fUsername;
    private String fPassword;
    private String fHead;
    private String fEmail;
    private Integer fSex;
    private List<AdminUserRole> adminUserRoles;

    public AdminUser() {
    }

    public AdminUser(Integer fId, String fAccount, String fUsername, String fPassword, String fHead, String fEmail, Integer fSex, List<AdminUserRole> adminUserRoles) {
        this.fId = fId;
        this.fAccount = fAccount;
        this.fUsername = fUsername;
        this.fPassword = fPassword;
        this.fHead = fHead;
        this.fEmail = fEmail;
        this.fSex = fSex;
        this.adminUserRoles = adminUserRoles;
    }

    public Integer getfId() {
        return fId;
    }

    public void setfId(Integer fId) {
        this.fId = fId;
    }

    public String getfAccount() {
        return fAccount;
    }

    public void setfAccount(String fAccount) {
        this.fAccount = fAccount;
    }

    public String getfUsername() {
        return fUsername;
    }

    public void setfUsername(String fUsername) {
        this.fUsername = fUsername;
    }

    public String getfPassword() {
        return fPassword;
    }

    public void setfPassword(String fPassword) {
        this.fPassword = fPassword;
    }

    public String getfHead() {
        return fHead;
    }

    public void setfHead(String fHead) {
        this.fHead = fHead;
    }

    public String getfEmail() {
        return fEmail;
    }

    public void setfEmail(String fEmail) {
        this.fEmail = fEmail;
    }

    public Integer getfSex() {
        return fSex;
    }

    public void setfSex(Integer fSex) {
        this.fSex = fSex;
    }

    public List<AdminUserRole> getAdminUserRoles() {
        return adminUserRoles;
    }

    public void setAdminUserRoles(List<AdminUserRole> adminUserRoles) {
        this.adminUserRoles = adminUserRoles;
    }

    @Override
    public String toString() {
        return "AdminUser{" +
                "fId=" + fId +
                ", fAccount='" + fAccount + '\'' +
                ", fUsername='" + fUsername + '\'' +
                ", fPassword='" + fPassword + '\'' +
                ", fHead='" + fHead + '\'' +
                ", fEmail='" + fEmail + '\'' +
                ", fSex=" + fSex +
                ", adminUserRoles=" + adminUserRoles +
                '}';
    }
}
