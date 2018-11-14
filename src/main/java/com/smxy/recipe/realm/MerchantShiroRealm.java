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
 * Build File @date: 2018/10/15 13:50
 * @Description TODO
 * @version 1.0
 */
package com.smxy.recipe.realm;

import com.smxy.recipe.dao.MerchantUserDao;
import com.smxy.recipe.entity.AdminPermission;
import com.smxy.recipe.entity.AdminRole;
import com.smxy.recipe.entity.AdminUser;
import com.smxy.recipe.entity.MerchantUser;
import com.smxy.recipe.service.AdminUserService;
import com.smxy.recipe.service.MerchantUserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

public class MerchantShiroRealm extends AuthorizingRealm {
    @Lazy
    @Autowired
    AdminUserService adminUserService;
    @Lazy
    @Autowired
    MerchantUserService merchantUserService;
    @Lazy
    @Autowired
    MerchantUserDao merchantUserDao;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        if (principalCollection.getPrimaryPrincipal() instanceof MerchantUser) {
            MerchantUser merchantUser = (MerchantUser) principalCollection.getPrimaryPrincipal();
            for (AdminRole adminRole : adminUserService.verifyRole(merchantUser.getFId())) {
                authorizationInfo.addRole(adminRole.getFRolename());
                for (AdminPermission adminPermission : adminUserService.verifyPermission(merchantUser.getFId())) {
                    authorizationInfo.addStringPermission(adminPermission.getFPermissionname());
                }
            }
        }
        return authorizationInfo;
    }

    /**
     * 身份认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UserToken token = (UserToken) authenticationToken;
        String account = token.getUsername();
        MerchantUser merchantUser = merchantUserDao.isMerchantUser(account);
        if (merchantUser == null) {
            return null;
        }
        Object principal = merchantUser;
        Object credentials = merchantUser.getFPassword();
        String realmName = getName();
        ByteSource credentialsSalt = ByteSource.Util.bytes(account);
        return new SimpleAuthenticationInfo(principal, credentials, credentialsSalt, realmName);
    }
}
