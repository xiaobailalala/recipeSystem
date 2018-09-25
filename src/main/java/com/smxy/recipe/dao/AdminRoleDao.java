/**
 * Copyright © 2018 eSunny Info. Developer Stu. All rights reserved.
 *
 * @Package:
 * @author: zpx
 * Build File @date: 2018/8/7 19:47
 * @Description TODO
 * @version 1.0
 */
package com.smxy.recipe.dao;

import com.smxy.recipe.entity.AdminRole;

import java.util.List;

public interface AdminRoleDao {
    AdminRole getAdminRoleByFid(int fId);

    List<AdminRole> getInfoAll();

    AdminRole getAdminRoleByName(AdminRole adminRole);

    Integer saveInfo(AdminRole adminRole);

    Integer updateInfo(AdminRole adminRole);

    Integer deleteInfo(Integer id);
}
