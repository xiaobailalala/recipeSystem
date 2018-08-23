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

import com.smxy.recipe.dao.AdminRoleDao;
import com.smxy.recipe.dao.AdminUserDao;
import com.smxy.recipe.dao.AdminUserRoleDao;
import com.smxy.recipe.entity.*;
import com.smxy.recipe.service.AdminRoleService;
import com.smxy.recipe.service.AdminUserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        AdminUser adminUser = (AdminUser)principalCollection.getPrimaryPrincipal();
//        Map<String, Object> map = adminUserService.getRoleAndPermission(adminUser.getfId()).getData();
//        for (AdminRole adminRole:(List<AdminRole>)map.get("roles")){
//            authorizationInfo.addRole(adminRole.getfRolename());
//            for (AdminPermission adminPermission:(List<AdminPermission>)map.get("permissions")){
//                authorizationInfo.addStringPermission(adminPermission.getfPermissionname());
//            }
//        }
        for (AdminRole adminRole:adminUserService.verifyRole(adminUser.getfId())){
            authorizationInfo.addRole(adminRole.getfRolename());
            for (AdminPermission adminPermission:adminUserService.verifyPermission(adminUser.getfId())){
                authorizationInfo.addStringPermission(adminPermission.getfPermissionname());
            }
        }
        return authorizationInfo;
    }
    /**
     * 身份认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token= (UsernamePasswordToken) authenticationToken;
        String account= token.getUsername();
        AdminUser adminUser=adminUserDao.isAdminUser(account);
        if (adminUser==null){
            return null;
        }
        Object principal=adminUser;
        Object credentials = adminUser.getfPassword();
        String realmName = getName();
        ByteSource credentialsSalt = ByteSource.Util.bytes(account);
        return new SimpleAuthenticationInfo(principal, credentials, credentialsSalt, realmName);
    }
}
