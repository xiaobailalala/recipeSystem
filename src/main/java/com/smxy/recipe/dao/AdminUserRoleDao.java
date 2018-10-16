/**
 * Copyright © 2018 eSunny Info. Developer Stu. All rights reserved.
 *
 * @Package:
 * @author: zpx
 * Build File @date: 2018/8/7 17:42
 * @Description TODO
 * @version 1.0
 */
package com.smxy.recipe.dao;

import com.smxy.recipe.entity.AdminUserRole;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;
import java.util.Map;

public interface AdminUserRoleDao {

    /**
     * 根据管理员的id查询 管理员-角色 表信息
     * @param fUid 管理员id
     * @return 返回的 管理员-角色 信息集合
     */
    List<AdminUserRole> getAdminUserRoleByFuid(Integer fUid);

    /**
     * 根据管理员的id和角色的id删除信息
     * @param map 集合包含管理员id和角色的id
     * @return 数据库更新数
     */
    Integer deleteInfoByUidAndRid(Map<String, Integer> map);

    /**
     * 增加 管理员-角色 表信息
     * @param map 集合包含管理员管理员id和角色id
     * @return 数据库更新数
     */
    Integer saveInfo(Map<String, Integer> map);

    /**
     * 根据管理员id删除管理员信息
     * @param fUid 管理员id
     * @return 数据库更新数
     */
    Integer deleteInfoByUid(Integer fUid);
}
