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
    List<AdminRolePermission> getAdminRolePermissionByFrid(Integer fRid);
    Integer deleteInfoByRidAndPid(Map<String, Integer> map);
    Integer saveInfo(Map<String, Integer> map);
    Integer deleteInfoByRid(Integer fRid);
}
