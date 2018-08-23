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

    public ResApi<Object> roleList();

    public ResApi<Object> isName(AdminRole adminRole);

    public ResApi<Object> saveRole(AdminRole adminRole);

    public AdminRole getOneById(Integer id);

    public ResApi<Object> updateRole(Integer id, AdminRole adminRole);

    public ResApi<Object> deleteRole(Integer id);

    public ResApi<Object> toPerm(Integer id);

    public ResApi<Object> deletePerm(Integer[] pid, Integer rid);

    public ResApi<Object> addPerm(Integer[] pid, Integer rid);

}
