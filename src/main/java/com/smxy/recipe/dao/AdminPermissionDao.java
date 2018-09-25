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

    AdminPermission getAdminPermissionByFid(int fId);

    List<AdminPermission> getAdminPermissionAll();

    AdminPermission getAdminPermissionByName(String fPermissionname);

    Integer saveInfo(AdminPermission adminPermission);

    Integer deleteInfo(Integer fId);

    Integer updateInfo(AdminPermission adminPermission);

}
