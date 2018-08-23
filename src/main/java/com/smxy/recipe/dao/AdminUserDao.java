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
    public AdminUser isAdminUser(String fAccount);

    public AdminUser isLogin(AdminUser adminUser);

    public List<AdminUser> getAdminUserAll();

    public AdminUser getAdminUserByFid(Integer fId);

    public int updateInfoByFid(AdminUser adminUser);

    public int updatePasswordByFid(AdminUser adminUser);

    public int updateEmailByFid(AdminUser adminUser);

    public int isEmail(String fEmail);

    public int deleteUser(Integer id);

    public int saveInfo(AdminUser adminUser);
}
