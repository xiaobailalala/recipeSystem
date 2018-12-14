package com.smxy.recipe.service.impl;

import com.smxy.recipe.dao.AdminRoleDao;
import com.smxy.recipe.dao.AdminUserRoleDao;
import com.smxy.recipe.dao.MerchantUserDao;
import com.smxy.recipe.dao.MerchantUserRoleDao;
import com.smxy.recipe.entity.*;
import com.smxy.recipe.realm.LoginType;
import com.smxy.recipe.realm.UserToken;
import com.smxy.recipe.service.MerchantUserService;
import com.smxy.recipe.utils.ResApi;
import com.smxy.recipe.utils.ToolsApi;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Demo MerchantUserServiceImpl
 *
 * @author Yangyihui
 * @date 2018/11/12 0012 21:36
 */
@Transactional(rollbackFor = Exception.class)
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
    @Autowired
    private MerchantUserRoleDao merchantUserRoleDao;

    private static final String MERCHANT_LOGIN_TYPE = LoginType.MERCHANT.toString();

    @Override
    public ResApi<String> userLogin(MerchantUser merchantUser, HttpServletRequest request) {
        ResApi<String> resApi = new ResApi<>(500, "系统出错", "error");
        Subject currentUser = SecurityUtils.getSubject();
        try {
            if (!currentUser.isAuthenticated()) {
                UserToken token = new UserToken(merchantUser.getFAccount(), merchantUser.getFPassword(), MERCHANT_LOGIN_TYPE);
                token.setRememberMe(false);
                try {
                    currentUser.login(token);
                    merchantUser = (MerchantUser) currentUser.getPrincipal();
                    SecurityUtils.getSubject().getSession().setAttribute("merUser", merchantUser);
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
            return resApi;
        }

    }

    @Override
    public ResApi<String> editorUserCoverById(MultipartFile editorImage, Integer fId, HttpServletRequest request) {
        MerchantUser oldMerchantUser = merchantUserDao.getMerchantUserById(fId);
        Map<String, Object> map = new HashMap<>(8);
        Subject subject = SecurityUtils.getSubject();
        if (editorImage.getSize() > 0) {
            ToolsApi.multipartFileDeleteFile(oldMerchantUser.getFCover());
            String filename = ToolsApi.multipartFileUploadFile(editorImage, null);
            map.put("fCover", filename);
            map.put("fId", fId);
            int result = merchantUserDao.updateUserCoverById(map);
            if (result > 0){
                subject.getSession().setAttribute("merUser", merchantUserDao.getMerchantUserById(fId));
                return ResApi.getSuccess();
            } else {
                return ResApi.getError();
            }
        } else {
            return ResApi.getError();
        }
    }

    @Override
    public ResApi<Object> mobUserLogin(MerchantUser merchantUser, HttpServletRequest request) {
        ResApi<Object> resApi = new ResApi<>(500, "系统出错", "error");
        Subject currentUser = SecurityUtils.getSubject();
        Map<String, Object> map = new HashMap<>(8);
        try {
            if (!currentUser.isAuthenticated()) {
                UserToken token = new UserToken(merchantUser.getFAccount(), merchantUser.getFPassword(), MERCHANT_LOGIN_TYPE);
                token.setRememberMe(false);
                try {
                    currentUser.login(token);
                    merchantUser = (MerchantUser) currentUser.getPrincipal();
                    SecurityUtils.getSubject().getSession().setAttribute("merUser", merchantUser);
                    map.put("token", currentUser.getSession().getId());
                    System.out.println(currentUser.getSession().getId());
                } catch (UnknownAccountException ae) {
                    resApi = new ResApi<>(501, "该账号不存在。", "failed");
                    return resApi;
                } catch (IncorrectCredentialsException ice) {
                    resApi = new ResApi<>(502, "您输入的密码不正确。", "failed");
                    return resApi;
                }
            }
            return new ResApi<>(200, "success", map);
        } catch (Exception e) {
            return resApi;
        }
    }

    @Override
    public ResApi<String> userRegister(MerchantUser merchantUser, HttpServletRequest request) {
        ResApi<String> resApi = new ResApi<>(500, "系统出错", "error");
        if (merchantUserDao.isAccount(merchantUser.getFAccount()) > 0) {
            return new ResApi<>(501, "手机号已存在", "phoneError");
        } else {
            int coverNum = (int) (Math.random() * 10 + 1);
            merchantUser.setFPassword(ToolsApi.entryptBySaltMd5(merchantUser.getFPassword(), merchantUser.getFAccount()));
            merchantUser.setFCover("/src/images/merchant_head/" + coverNum + ".jpg");
            if (merchantUserDao.saveUserInfo(merchantUser) > 0) {
                resApi = new ResApi<>(200, "注册成功", "success");
//                request.getSession().setAttribute("merUser", merchantUser);
                Map<String, Integer> map = new HashMap<>(16);
                map.put("fMid", merchantUser.getFId());
                AdminRole role = new AdminRole();
                role.setFRolename("merchant");
                AdminRole adminRole = adminRoleDao.getAdminRoleByName(role);
                map.put("fRid", adminRole.getFId());
                adminUserRoleDao.saveMerchantInfo(map);
            }
            return resApi;
        }
    }

    @Cacheable(value = "MerchantVerifyRole", key = "#mid")
    @Override
    public List<AdminRole> verifyRole(Integer mid) {
        List<AdminRole> adminRoles = new ArrayList<>();
        for (AdminUserRole adminUserRole : merchantUserRoleDao.getMerchantUserRoleByFmid(mid)) {
            adminRoles.add(adminUserRole.getAdminRole());
        }
        return adminRoles;
    }

    @Cacheable(value = "MerchantVerifyPermission", key = "#mid")
    @Override
    public List<AdminPermission> verifyPermission(Integer mid) {
        List<AdminPermission> adminPermissions = new ArrayList<>();
        for (AdminUserRole adminUserRole : merchantUserRoleDao.getMerchantUserRoleByFmid(mid)) {
            for (AdminRolePermission adminRolePermission : adminUserRole.getAdminRole().getAdminRolePermissions()) {
                adminPermissions.add(adminRolePermission.getAdminPermission());
            }
        }
        return adminPermissions;
    }

    @Override
    public ResApi<String> editorUserDetails(MerchantUser merchantUser, Integer fId) {
        MerchantUser oldMerchantUser = merchantUserDao.getMerchantUserById(fId);
        merchantUser.setFId(fId);
        if ("".equals(merchantUser.getFProvince())) {
            merchantUser.setFProvince(oldMerchantUser.getFProvince());
        }
        if ("".equals(merchantUser.getFCity())) {
            merchantUser.setFCity(oldMerchantUser.getFCity());
        }
        if ("".equals(merchantUser.getFArea())) {
            merchantUser.setFArea(oldMerchantUser.getFArea());
        }
        if ("".equals(merchantUser.getFStreet())) {
            merchantUser.setFStreet(oldMerchantUser.getFStreet());
        }
        if ("".equals(merchantUser.getFSignature())) {
            merchantUser.setFSignature(oldMerchantUser.getFSignature());
        }
        Subject subject = SecurityUtils.getSubject();
        Integer result = merchantUserDao.updateMerchantUserInfo(merchantUser);
        if (result > 0) {
            subject.getSession().setAttribute("merUser", merchantUserDao.getMerchantUserById(fId));
            return ResApi.getSuccess();
        } else {
            return ResApi.getError();
        }
    }

    @Override
    public ResApi<String> editorUserPassword(String fPassword, String oldPassword, Integer fId) {
        System.out.println(oldPassword + "f" + fPassword);
        Map<String, Object> map = new HashMap<>(8);
        MerchantUser merchantUser = merchantUserDao.getMerchantUserById(fId);
        String oldPass = ToolsApi.entryptBySaltMd5(oldPassword, merchantUser.getFAccount());
        if (oldPass.equals(merchantUser.getFPassword())) {
            String password = ToolsApi.entryptBySaltMd5(fPassword, merchantUser.getFAccount());
            map.put("fPassword", password);
            map.put("fId", fId);
            Integer result = merchantUserDao.updateMerchantUserPasswordById(map);
            if (result > 0) {
                return ResApi.getSuccess();
            } else {
                return ResApi.getError();
            }
        } else {
            return new ResApi<>(408, "原密码错误", "passwordError");
        }
    }
}
