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

import java.util.List;

public interface AdminPermissionService {

    ResApi<Object> permissionList();

    ResApi<Object> isName(AdminPermission adminPermission);

    ResApi<Object> saveInfo(AdminPermission adminPermission);

    ResApi<Object> deleteInfo(Integer id);

    AdminPermission getOneByFid(Integer id);

    ResApi<Object> updateInfo(Integer id,AdminPermission adminPermission);

}
