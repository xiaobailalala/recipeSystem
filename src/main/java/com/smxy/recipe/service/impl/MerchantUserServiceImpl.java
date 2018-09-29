package com.smxy.recipe.service.impl;

import com.smxy.recipe.dao.MerchantUserDao;
import com.smxy.recipe.entity.MerchantUser;
import com.smxy.recipe.service.MerchantUserService;
import com.smxy.recipe.utils.ResApi;
import com.smxy.recipe.utils.ToolsApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("merchantUserService")
public class MerchantUserServiceImpl implements MerchantUserService {

    @Autowired
    MerchantUserDao merchant_userDao;


    @Override
    public ResApi<MerchantUser> merchantUserLogin(MerchantUser merchant_user) {
        ResApi<MerchantUser> resApi;
        if (merchant_userDao.isUser(merchant_user) > 0) {
            merchant_user.setFPassword(ToolsApi.toMD5(merchant_user.getFPassword()));
            merchant_user = merchant_userDao.isLogin(merchant_user);
            if (merchant_user != null) {
                resApi = new ResApi<>(200, "登陆成功", merchant_user);
            } else {
                resApi = new ResApi<>(401, "密码不匹配", null);
            }
        } else {
            resApi = new ResApi<>(400, "用户不存在,请先注册", null);
        }
        return resApi;
    }

    @Override
    public ResApi<String> merchantUserReg(MerchantUser merchant_user) {
        ResApi<String> resApi;
        if (merchant_userDao.isUser(merchant_user) > 0) {
            resApi = new ResApi<>(400, "账户已存在", null);
        } else {
            merchant_user.setFPassword(ToolsApi.toMD5(merchant_user.getFPassword()));
            if (merchant_userDao.saveMerchantUser(merchant_user) > 0) {
                merchant_user.setFUsername("膳客商户" + merchant_user.getFId());
                if (merchant_userDao.updateUserInfo(merchant_user) > 0) {
                    resApi = new ResApi<>(200, "注册成功", null);
                } else {
                    resApi = new ResApi<>(401, "注册失败", null);
                }
            } else {
                resApi = new ResApi<>(400, "添加用户失败，请重试", null);
            }
        }
        return resApi;
    }

}
