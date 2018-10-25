/**
 * Copyright © 2018 eSunny Info. Developer Stu. All rights reserved.
 * <p>
 * code is far away from bug with the animal protecting
 * <p>
 * ┏┓　　　┏┓
 * ┏┛┻━━━┛┻┓
 * ┃　　　　　　　┃
 * ┃　　　━　　　┃
 * ┃　┳┛　┗┳　┃
 * ┃　　　　　　　┃
 * ┃　　　┻　　　┃
 * ┃　　　　　　　┃
 * ┗━┓　　　┏━┛
 * 　　┃　　　┃神兽保佑
 * 　　┃　　　┃代码无BUG！
 * 　　┃　　　┗━━━┓
 * 　　┃　　　　　　　┣┓
 * 　　┃　　　　　　　┏┛
 * 　　┗┓┓┏━┳┓┏┛
 * 　　　┃┫┫　┃┫┫
 * 　　　┗┻┛　┗┻┛
 *
 * @Package:
 * @author: zpx
 * Build File @date: 2018/9/30 7:48
 * @Description TODO
 * @version 1.0
 */
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
    private MerchantUserDao merchantUserDao;


    @Override
    public ResApi<MerchantUser> merchantUserLogin(MerchantUser merchantUser) {
        ResApi<MerchantUser> resApi;
        if (merchantUserDao.isUser(merchantUser) > 0) {
            merchantUser.setFPassword(ToolsApi.toMD5(merchantUser.getFPassword()));
            merchantUser = merchantUserDao.isLogin(merchantUser);
            if (merchantUser != null) {
                resApi = new ResApi<>(200, "登陆成功", merchantUser);
            } else {
                resApi = new ResApi<>(401, "密码不匹配", null);
            }
        } else {
            resApi = new ResApi<>(400, "用户不存在,请先注册", null);
        }
        return resApi;
    }

    @Override
    public ResApi<String> merchantUserReg(MerchantUser merchantUser) {
        ResApi<String> resApi;
        if (merchantUserDao.isUser(merchantUser) > 0) {
            resApi = new ResApi<>(400, "账户已存在", null);
        } else {
            merchantUser.setFPassword(ToolsApi.toMD5(merchantUser.getFPassword()));
            if (merchantUserDao.saveMerchantUser(merchantUser) > 0) {
                merchantUser.setFUsername("膳客商户" + merchantUser.getFId());
                if (merchantUserDao.updateUserInfo(merchantUser) > 0) {
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
