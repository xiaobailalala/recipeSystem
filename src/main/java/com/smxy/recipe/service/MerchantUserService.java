package com.smxy.recipe.service;

import com.smxy.recipe.entity.MerchantUser;
import com.smxy.recipe.utils.ResApi;

public interface MerchantUserService {
    ResApi<MerchantUser> merchantUserLogin(MerchantUser merchant_user);
    ResApi<String> merchantUserReg(MerchantUser merchant_user);
}
