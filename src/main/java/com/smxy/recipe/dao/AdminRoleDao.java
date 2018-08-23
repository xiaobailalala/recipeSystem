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
    public AdminRole getAdminRoleByFid(int fId);

    public List<AdminRole> getInfoAll();

    public AdminRole getAdminRoleByName(AdminRole adminRole);

    public int saveInfo(AdminRole adminRole);

    public int updateInfo(AdminRole adminRole);

    public int deleteInfo(Integer id);
}
