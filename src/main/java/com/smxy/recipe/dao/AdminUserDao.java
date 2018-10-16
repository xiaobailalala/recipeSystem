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

    /**
     * 根据管理员账号查找管理员
     * @param fAccount 管理员账号
     * @return 一条管理员信息
     */
    AdminUser isAdminUser(String fAccount);

    /**
     * 根据管路员账号和密码查找管理员
     * @param adminUser 管理员信息类
     * @return 一条管理员信息
     */
    AdminUser isLogin(AdminUser adminUser);

    /**
     * 查询所有管理员
     * @return 管理员集合
     */
    List<AdminUser> getAdminUserAll();

    /**
     * 根据管理员的id查询管理员信息
     * @param fId 管理员的id
     * @return 返回一条管理员信息
     */
    AdminUser getAdminUserByFid(Integer fId);

    /**
     * 根据管理员的id更新管理员基础信息
     * @param adminUser 管理员信息类
     * @return 数据库更新数
     */
    Integer updateInfoByFid(AdminUser adminUser);

    /**
     * 根据管理员的id更新管理员的密码
     * @param adminUser 管理员信息类
     * @return 数据库更新数
     */
    Integer updatePasswordByFid(AdminUser adminUser);

    /**
     * 根据管理员的id更新管理员的邮箱
     * @param adminUser 管理员信息类
     * @return 数据库更新数
     */
    Integer updateEmailByFid(AdminUser adminUser);

    /**
     * 根据邮箱号判断管理员是否有邮箱
     * @param fEmail 邮箱号
     * @return 数据库更新数
     */
    Integer isEmail(String fEmail);

    /**
     * 根据管理员的id删除管理员信息
     * @param id 管理员id
     * @return 数据库更新数
     */
    Integer deleteUser(Integer id);

    /**
     * 增加管理员信息
     * @param adminUser 管理员信息类
     * @return 数据库更新数
     */
    Integer saveInfo(AdminUser adminUser);

    /**
     * 根据管理员的id查询管理员信息（简略信息）
     * @param fId 管理员的id
     * @return 返回一条管理员信息
     */
    AdminUser getInfoByIdBrief(Integer fId);
}
