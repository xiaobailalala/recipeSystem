/**
 * Copyright © 2018 eSunny Info. Developer Stu. All rights reserved.
 *
 * @Package:
 * @author: zpx
 * Build File @date: 2018/8/7 20:05
 * @Description TODO
 * @version 1.0
 */
package com.smxy.recipe.dao;

import com.smxy.recipe.entity.AdminPermission;

import java.util.List;

public interface AdminPermissionDao {

    public AdminPermission getAdminPermissionByFid(int fId);

    public List<AdminPermission> getAdminPermissionAll();

    public AdminPermission getAdminPermissionByName(String fPermissionname);

    public int saveInfo(AdminPermission adminPermission);

    public int deleteInfo(Integer fId);

    public int updateInfo(AdminPermission adminPermission);

}
