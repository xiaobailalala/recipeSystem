package com.smxy.recipe.dao;

import com.smxy.recipe.entity.AdminUserRole;

import java.util.List;

/**
 * Demo MerchantUserRoleDao
 *
 * @author Yangyihui
 * @date 2018/11/17 0017 20:32
 */
public interface MerchantUserRoleDao {
    /**
     * 功能描述: 根据商家ID查询 商家-角色 表信息
     * @param fMid 商家ID
     * @return 返回 商家-角色 信息集合
     * @auther: yangyihui
     * @date: 2018/11/17 0017 19:25
     */
    List<AdminUserRole> getMerchantUserRoleByFmid(Integer fMid);
}
