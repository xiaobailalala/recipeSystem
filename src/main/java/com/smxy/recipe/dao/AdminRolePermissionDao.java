/**
 * Copyright © 2018 eSunny Info. Developer Stu. All rights reserved.
 *
 * @Package:
 * @author: zpx
 * Build File @date: 2018/8/7 19:55
 * @Description TODO
 * @version 1.0
 */
package com.smxy.recipe.dao;

import com.smxy.recipe.entity.AdminRolePermission;

import java.util.List;
import java.util.Map;

public interface AdminRolePermissionDao {

    /**
     * 根据角色的id查询 角色-权限 表的对应信息
     * @param fRid 角色的id
     * @return 角色-权限 集合
     */
    List<AdminRolePermission> getAdminRolePermissionByFrid(Integer fRid);

    /**
     * 根据角色id和权限id删除一条信息
     * @param map 集合中包含角色id和权限id
     * @return 数据库更新数
     */
    Integer deleteInfoByRidAndPid(Map<String, Integer> map);

    /**
     * 增加一条 角色-权限 信息
     * @param map 集合中包含角色id和权限id
     * @return 数据库更新数
     */
    Integer saveInfo(Map<String, Integer> map);

    /**
     * 根据角色id删除一条 角色-权限 信息
     * @param fRid 角色的id
     * @return 数据库更新数
     */
    Integer deleteInfoByRid(Integer fRid);
}
