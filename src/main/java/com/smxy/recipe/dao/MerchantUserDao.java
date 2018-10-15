package com.smxy.recipe.dao;

import com.smxy.recipe.entity.MerchantUser;

public interface MerchantUserDao {
    Integer saveMerchantUser(MerchantUser merchant_user);
    Integer isUser(MerchantUser merchant_user);
    Integer updateUserInfo(MerchantUser merchant_user);
    MerchantUser isLogin(MerchantUser merchant_user);
}
