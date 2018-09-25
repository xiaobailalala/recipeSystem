/**
 * Copyright © 2018 eSunny Info. Developer Stu. All rights reserved.
 *
 * @Package:
 * @author: zpx
 * Build File @date: 2018/8/16 16:26
 * @Description TODO
 * @version 1.0
 */
package com.smxy.recipe.service;

import com.smxy.recipe.entity.AdminRole;
import com.smxy.recipe.utils.ResApi;

import java.util.List;

public interface AdminRoleService {

    ResApi<Object> roleList();

    ResApi<Object> isName(AdminRole adminRole);

    ResApi<Object> saveRole(AdminRole adminRole);

    AdminRole getOneById(Integer id);

    ResApi<Object> updateRole(Integer id, AdminRole adminRole);

    ResApi<Object> deleteRole(Integer id);

    ResApi<Object> toPerm(Integer id);

    ResApi<Object> deletePerm(Integer[] pid, Integer rid);

    ResApi<Object> addPerm(Integer[] pid, Integer rid);

}
