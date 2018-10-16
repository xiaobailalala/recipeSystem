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

    /**
     * 根据权限id查找一条权限信息
     * @param fId 权限id
     * @return 一条权限信息
     */
    AdminPermission getAdminPermissionByFid(int fId);

    /**
     * 查找所有权限信息
     * @return 权限集合
     */
    List<AdminPermission> getAdminPermissionAll();

    /**
     * 根据权限名查找一条权限信息
     * @param fPermissionname 权限名Str
     * @return 一条权限信息
     */
    AdminPermission getAdminPermissionByName(String fPermissionname);

    /**
     * 增加一条权限信息
     * @param adminPermission 要保存的权限信息类
     * @return 数据库更新数
     */
    Integer saveInfo(AdminPermission adminPermission);

    /**
     * 根据权限id删除一条权限信息
     * @param fId 要删除的权限的id
     * @return 数据库更新数
     */
    Integer deleteInfo(Integer fId);

    /**
     * 根据权限id更新一条权限信息
     * @param adminPermission 要更新的权限信息类
     * @return 数据库更新数
     */
    Integer updateInfo(AdminPermission adminPermission);

}
