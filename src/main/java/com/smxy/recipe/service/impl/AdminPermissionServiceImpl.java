/**
 * Copyright © 2018 eSunny Info. Developer Stu. All rights reserved.
 *
 * @Package:
 * @author: zpx
 * Build File @date: 2018/8/15 16:31
 * @Description TODO
 * @version 1.0
 */
package com.smxy.recipe.service.impl;

import com.smxy.recipe.dao.AdminPermissionDao;
import com.smxy.recipe.entity.AdminPermission;
import com.smxy.recipe.service.AdminPermissionService;
import com.smxy.recipe.utils.ResApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(rollbackFor = Exception.class)
@Service("adminPermissionService")
public class AdminPermissionServiceImpl implements AdminPermissionService {

    private AdminPermissionDao adminPermissionDao;

    @Autowired
    public AdminPermissionServiceImpl(AdminPermissionDao adminPermissionDao) {
        this.adminPermissionDao = adminPermissionDao;
    }

    @Override
    public ResApi<Object> permissionList() {
        return ResApi.getSuccess(adminPermissionDao.getAdminPermissionAll());
    }

    @Override
    public ResApi<String> isName(AdminPermission adminPermission) {
        if (adminPermissionDao.getAdminPermissionByName(adminPermission.getFPermissionname())!=null){
            return ResApi.getError(501, "该权限代号已存在，请重新提交。");
        }else{
            return ResApi.getSuccess();
        }
    }

    @CacheEvict(value = {"verifyRole", "verifyPermission"}, allEntries = true)
    @Override
    public ResApi<String> saveInfo(AdminPermission adminPermission) {
        if (adminPermissionDao.saveInfo(adminPermission)>0){
            return ResApi.getSuccess();
        }
        return ResApi.getError();
    }

    @CacheEvict(value = {"verifyRole", "verifyPermission"}, allEntries = true)
    @Override
    public ResApi<String> deleteInfo(Integer id) {
        if (adminPermissionDao.deleteInfo(id)>0){
            return ResApi.getSuccess();
        }
        return ResApi.getError();
    }

    @Override
    public AdminPermission getOneByFid(Integer id) {
        return adminPermissionDao.getAdminPermissionByFid(id);
    }

    @CacheEvict(value = {"verifyRole", "verifyPermission"}, allEntries = true)
    @Override
    public ResApi<String> updateInfo(Integer id, AdminPermission adminPermission) {
        adminPermission.setFId(id);
        if (!adminPermission.getFPermissionname().equals(adminPermissionDao.getAdminPermissionByFid(adminPermission.getFId()).getFPermissionname())&&
                adminPermissionDao.getAdminPermissionByName(adminPermission.getFPermissionname())!=null){
            return ResApi.getError(501, "该权限代号已存在，请重新提交。");
        }else{
            if (adminPermissionDao.updateInfo(adminPermission)>0){
                return ResApi.getSuccess();
            }
        }
        return ResApi.getError();
    }
}
