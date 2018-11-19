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

public interface AdminRoleService {

    ResApi<Object> roleList();

    ResApi<String> isName(AdminRole adminRole);

    ResApi<String> saveRole(AdminRole adminRole);

    AdminRole getOneById(Integer id);

    ResApi<String> updateRole(Integer id, AdminRole adminRole);

    ResApi<String> deleteRole(Integer id);

    ResApi<Object> toPerm(Integer id);

    ResApi<String> deletePerm(Integer[] pid, Integer rid);

    ResApi<String> addPerm(Integer[] pid, Integer rid);

}
