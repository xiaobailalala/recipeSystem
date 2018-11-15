/**
 * Copyright © 2018 eSunny Info. Developer Stu. All rights reserved.
 *
 * @Package:
 * @author: zpx
 * Build File @date: 2018/8/6 20:11
 * @Description TODO
 * @version 1.0
 */
package com.smxy.recipe.realm;

import com.smxy.recipe.dao.AdminUserDao;
import com.smxy.recipe.entity.AdminPermission;
import com.smxy.recipe.entity.AdminRole;
import com.smxy.recipe.entity.AdminUser;
import com.smxy.recipe.entity.MerchantUser;
import com.smxy.recipe.service.AdminUserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

public class AdminShiroRealm extends AuthorizingRealm {

    @Autowired
    @Lazy
    private AdminUserDao adminUserDao;
    @Autowired
    @Lazy
    private AdminUserService adminUserService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        adminAuthorizationInfo(principalCollection, authorizationInfo, adminUserService);
        MerchantShiroRealm.merchantAuthorization(principalCollection, authorizationInfo, adminUserService);
        return authorizationInfo;
    }

    static void adminAuthorizationInfo(PrincipalCollection principalCollection, SimpleAuthorizationInfo authorizationInfo, AdminUserService adminUserService) {
        if (principalCollection.getPrimaryPrincipal() instanceof AdminUser) {
            System.out.println("------AdminUser");
            AdminUser adminUser = (AdminUser) principalCollection.getPrimaryPrincipal();
            for (AdminRole adminRole : adminUserService.verifyRole(adminUser.getFId())) {
                authorizationInfo.addRole(adminRole.getFRolename());
                for (AdminPermission adminPermission : adminUserService.verifyPermission(adminUser.getFId())) {
                    authorizationInfo.addStringPermission(adminPermission.getFPermissionname());
                }
            }
        }
    }

    /**
     * 身份认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
//        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        UserToken token = (UserToken) authenticationToken;
        String account = token.getUsername();
        AdminUser adminUser = adminUserDao.isAdminUser(account);
        if (adminUser == null) {
            return null;
        }
        Object principal = adminUser;
        Object credentials = adminUser.getFPassword();
        String realmName = getName();
        ByteSource credentialsSalt = ByteSource.Util.bytes(account);
        return new SimpleAuthenticationInfo(principal, credentials, credentialsSalt, realmName);
    }
}
