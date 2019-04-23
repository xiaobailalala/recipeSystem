package com.smxy.recipe.dao;

import com.smxy.recipe.entity.CommonUser;

import java.util.List;

/**
 * Demo MerchantUserCommonUserDao
 *
 * @author Yangyihui
 * @date 2019/4/21 0021 16:13
 */
public interface MerchantUserCommonUserDao {

    /**
     * 功能描述: 根据商品id查询所有用户列表
     * @param fMid 1
     * @return : java.util.List<com.smxy.recipe.entity.CommonUser>
     * @author : yangyihui
     * @date : 2019/4/21 0021 16:18
     */
    List<CommonUser> getUserCountById(Integer fMid);
}
