package com.smxy.recipe.service.impl;

import com.smxy.recipe.dao.AdminRoleDao;
import com.smxy.recipe.dao.AdminUserRoleDao;
import com.smxy.recipe.dao.MerchantUserDao;
import com.smxy.recipe.entity.AdminRole;
import com.smxy.recipe.entity.AdminUserRole;
import com.smxy.recipe.entity.MerchantUser;
import com.smxy.recipe.realm.LoginType;
import com.smxy.recipe.realm.UserToken;
import com.smxy.recipe.service.MerchantUserService;
import com.smxy.recipe.utils.ResApi;
import com.smxy.recipe.utils.ToolsApi;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Demo MerchantUserServiceImpl
 *
 * @author Yangyihui
 * @date 2018/11/12 0012 21:36
 */
@Service("merchantUserService")
public class MerchantUserServiceImpl implements MerchantUserService {
    @Autowired
    private MerchantUserDao merchantUserDao;
    @Autowired
    private ToolsApi toolsApi;
    @Autowired
    private AdminUserRoleDao adminUserRoleDao;
    @Autowired
    private AdminRoleDao adminRoleDao;

    private static final String MERCHANT_LOGIN_TYPE = LoginType.MERCHANT.toString();

    @Override
    public ResApi<String> userLogin(MerchantUser merchantUser, HttpServletRequest request) {
        ResApi<String> resApi = new ResApi<>(500, "aaaaaa", "error");
        Subject currentUser = SecurityUtils.getSubject();
        try {
            if (!currentUser.isAuthenticated()) {
                UserToken token = new UserToken(merchantUser.getFAccount(), merchantUser.getFPassword(), MERCHANT_LOGIN_TYPE);
                try {
                    currentUser.login(token);
                    merchantUser = (MerchantUser) currentUser.getPrincipal();
                    request.getSession().setAttribute("merUser", merchantUser);
                } catch (UnknownAccountException ae) {
                    resApi = new ResApi<>(501, "该账号不存在。", "failed");
                    return resApi;
                } catch (IncorrectCredentialsException ice) {
                    resApi = new ResApi<>(502, "您输入的密码不正确。", "failed");
                    return resApi;
                }
            }
            return new ResApi<>(200, "success", "success");
        } catch (Exception e) {
            e.printStackTrace();
            return resApi;
        }

    }

    @Override
    public ResApi<String> userRegister(MerchantUser merchantUser, HttpServletRequest request) {
        ResApi<String> resApi = new ResApi<>(500, "系统出错", "error");
        if (merchantUserDao.isAccount(merchantUser.getFAccount()) > 0) {
            return new ResApi<>(501, "手机号已存在", "phoneError");
        } else {
            int coverNum = (int) (Math.random()*10+1);
            merchantUser.setFPassword(ToolsApi.entryptBySaltMd5(merchantUser.getFPassword(), merchantUser.getFAccount()));
            merchantUser.setFCover("/src/images/merchant_head/shengxiao_" + coverNum + ".jpg");
            if (merchantUserDao.saveUserInfo(merchantUser) > 0) {
                resApi = new ResApi<>(200, "注册成功", "success");
                request.getSession().setAttribute("merUser", merchantUser);
                Map<String, Integer> map = new HashMap<>(16);
                map.put("fUid", merchantUser.getFId());
                AdminRole role = new AdminRole();
                role.setFRolename("merchant");
                AdminRole adminRole = adminRoleDao.getAdminRoleByName(role);
                map.put("fRid",adminRole.getFId());
                adminUserRoleDao.saveInfo(map);
            }
            return resApi;
        }
    }
}
