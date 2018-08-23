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

    public List<AdminUserRole> getAdminUserRoleByFuid(int fUid);

    public int deleteInfoByUidAndRid(Map<String, Integer> map);

    public int saveInfo(Map<String, Integer> map);

    public int deleteInfoByUid(Integer fUid);
}
