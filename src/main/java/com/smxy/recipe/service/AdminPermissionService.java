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

    public ResApi<Object> permissionList();

    public ResApi<Object> isName(AdminPermission adminPermission);

    public ResApi<Object> saveInfo(AdminPermission adminPermission);

    public ResApi<Object> deleteInfo(Integer id);

    public AdminPermission getOneByFid(Integer id);

    public ResApi<Object> updateInfo(Integer id,AdminPermission adminPermission);

}
