/**
 * Copyright © 2018 eSunny Info. Developer Stu. All rights reserved.
 *
 * @Package:
 * @author: zpx
 * Build File @date: 2018/8/5 22:31
 * @Description TODO
 * @version 1.0
 */
package com.smxy.recipe.entity;

import java.io.Serializable;

public class AdminPermission implements Serializable {
    private Integer fId;
    private String fPermissionname;
    private String fIntroduction;

    public AdminPermission() {
    }

    public AdminPermission(Integer fId, String fPermissionname, String fIntroduction) {
        this.fId = fId;
        this.fPermissionname = fPermissionname;
        this.fIntroduction = fIntroduction;
    }

    public Integer getfId() {
        return fId;
    }

    public void setfId(Integer fId) {
        this.fId = fId;
    }

    public String getfPermissionname() {
        return fPermissionname;
    }

    public void setfPermissionname(String fPermissionname) {
        this.fPermissionname = fPermissionname;
    }

    public String getfIntroduction() {
        return fIntroduction;
    }

    public void setfIntroduction(String fIntroduction) {
        this.fIntroduction = fIntroduction;
    }

    @Override
    public String toString() {
        return "AdminPermission{" +
                "fId=" + fId +
                ", fPermissionname='" + fPermissionname + '\'' +
                ", fIntroduction='" + fIntroduction + '\'' +
                '}';
    }
}
