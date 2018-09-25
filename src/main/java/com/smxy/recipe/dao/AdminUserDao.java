/**
 * Copyright © 2018 eSunny Info. Developer Stu. All rights reserved.
 *
 * @Package:
 * @author: zpx
 * Build File @date: 2018/8/5 22:47
 * @Description TODO
 * @version 1.0
 */
package com.smxy.recipe.dao;

import com.smxy.recipe.entity.AdminUser;

import java.util.List;

public interface AdminUserDao {
    AdminUser isAdminUser(String fAccount);

    AdminUser isLogin(AdminUser adminUser);

    List<AdminUser> getAdminUserAll();

    AdminUser getAdminUserByFid(Integer fId);

    Integer updateInfoByFid(AdminUser adminUser);

    Integer updatePasswordByFid(AdminUser adminUser);

    Integer updateEmailByFid(AdminUser adminUser);

    Integer isEmail(String fEmail);

    Integer deleteUser(Integer id);

    Integer saveInfo(AdminUser adminUser);
}
