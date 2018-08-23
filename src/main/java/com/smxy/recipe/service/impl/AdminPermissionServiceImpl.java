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

import java.util.List;

@Service("adminPermissionService")
public class AdminPermissionServiceImpl implements AdminPermissionService {

    @Autowired
    private AdminPermissionDao adminPermissionDao;

    @Override
    public ResApi<Object> permissionList() {
        return new ResApi<>(200,"success",adminPermissionDao.getAdminPermissionAll());
    }

    @Override
    public ResApi<Object> isName(AdminPermission adminPermission) {
        ResApi<Object> resApi;
        if (adminPermissionDao.getAdminPermissionByName(adminPermission.getfPermissionname())!=null){
            resApi=new ResApi<>(501,"该权限代号已存在，请重新提交。","failed");
        }else{
            resApi=new ResApi<>(200,"success","success");
        }
        return resApi;
    }

    @CacheEvict(value = {"verifyRole", "verifyPermission"}, allEntries = true)
    @Override
    public ResApi<Object> saveInfo(AdminPermission adminPermission) {
        ResApi<Object> resApi=new ResApi<>(500,"系统出错了。","error");
        if (adminPermissionDao.saveInfo(adminPermission)>0){
            resApi=new ResApi<>(200,"success","success");
        }
        return resApi;
    }

    @CacheEvict(value = {"verifyRole", "verifyPermission"}, allEntries = true)
    @Override
    public ResApi<Object> deleteInfo(Integer id) {
        ResApi<Object> resApi=new ResApi<>(500,"系统出错了。","error");
        if (adminPermissionDao.deleteInfo(id)>0){
            resApi=new ResApi<>(200,"success","success");
        }
        return resApi;
    }

    @Override
    public AdminPermission getOneByFid(Integer id) {
        return adminPermissionDao.getAdminPermissionByFid(id);
    }

    @CacheEvict(value = {"verifyRole", "verifyPermission"}, allEntries = true)
    @Override
    public ResApi<Object> updateInfo(Integer id, AdminPermission adminPermission) {
        ResApi<Object> resApi=new ResApi<>(500,"系统出错了。","error");
        adminPermission.setfId(id);
        if (!adminPermission.getfPermissionname().equals(adminPermissionDao.getAdminPermissionByFid(adminPermission.getfId()).getfPermissionname())&&
                adminPermissionDao.getAdminPermissionByName(adminPermission.getfPermissionname())!=null){
            resApi=new ResApi<>(501,"该权限代号已存在，请重新提交。","failed");
        }else{
            if (adminPermissionDao.updateInfo(adminPermission)>0){
                resApi=new ResApi<>(200,"success","success");
            }
        }
        return resApi;
    }
}
