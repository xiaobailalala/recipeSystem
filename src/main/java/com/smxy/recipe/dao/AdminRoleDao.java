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

    /**
     * 根据角色的id查出一条角色信息
     * @param fId 角色id
     * @return 一条角色信息
     */
    AdminRole getAdminRoleByFid(int fId);

    /**
     * 查出所有的角色
     * @return 角色集合
     */
    List<AdminRole> getInfoAll();

    /**
     * 根据角色名查出一条角色信息
     * @param adminRole 角色信息类
     * @return 一条角色信息
     */
    AdminRole getAdminRoleByName(AdminRole adminRole);

    /**
     * 增加一条角色信息
     * @param adminRole 要保存的角色信息类
     * @return 数据库更新数
     */
    Integer saveInfo(AdminRole adminRole);

    /**
     * 根据角色id更新一条角色信息
     * @param adminRole 要更新的角色信息类
     * @return 数据库更新数
     */
    Integer updateInfo(AdminRole adminRole);

    /**
     * 根据角色id删除一条角色信息
     * @param id 要删除的角色id
     * @return 数据库更新数
     */
    Integer deleteInfo(Integer id);
}
