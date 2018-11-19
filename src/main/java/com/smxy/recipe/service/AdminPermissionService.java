/**
 * Copyright © 2018 eSunny Info. Developer Stu. All rights reserved.
 *
 * @Package:
 * @author: zpx
 * Build File @date: 2018/8/15 16:30
 * @Description TODO
 * @version 1.0
 */
package com.smxy.recipe.service;

import com.smxy.recipe.entity.AdminPermission;
import com.smxy.recipe.utils.ResApi;

public interface AdminPermissionService {

    ResApi<Object> permissionList();

    ResApi<String> isName(AdminPermission adminPermission);

    ResApi<String> saveInfo(AdminPermission adminPermission);

    ResApi<String> deleteInfo(Integer id);

    AdminPermission getOneByFid(Integer id);

    ResApi<String> updateInfo(Integer id, AdminPermission adminPermission);

}
