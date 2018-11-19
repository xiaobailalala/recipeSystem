/**
 * Copyright © 2018 eSunny Info. Developer Stu. All rights reserved.
 *
 * @Package:
 * @author: zpx
 * Build File @date: 2018/8/16 16:26
 * @Description TODO
 * @version 1.0
 */
package com.smxy.recipe.service.impl;

import com.smxy.recipe.dao.*;
import com.smxy.recipe.entity.AdminPermission;
import com.smxy.recipe.entity.AdminRole;
import com.smxy.recipe.service.AdminRoleService;
import com.smxy.recipe.utils.ResApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("adminRoleService")
public class AdminRoleServiceImpl implements AdminRoleService {

    private AdminRoleDao adminRoleDao;
    private AdminRolePermissionDao adminRolePermissionDao;
    private AdminPermissionDao adminPermissionDao;

    @Autowired
    public AdminRoleServiceImpl(AdminRoleDao adminRoleDao, AdminRolePermissionDao adminRolePermissionDao, AdminPermissionDao adminPermissionDao) {
        this.adminRoleDao = adminRoleDao;
        this.adminRolePermissionDao = adminRolePermissionDao;
        this.adminPermissionDao = adminPermissionDao;
    }

    @Override
    public ResApi<Object> roleList() {
        return ResApi.getSuccess(adminRoleDao.getInfoAll());
    }

    @Override
    public ResApi<String> isName(AdminRole adminRole) {
        if (adminRoleDao.getAdminRoleByName(adminRole)!=null){
            return ResApi.getError(501, "该角色已添加，请勿重复添加。");
        }else{
            return ResApi.getSuccess();
        }
    }

    @CacheEvict(value = {"verifyRole", "verifyPermission"}, allEntries = true)
    @Override
    public ResApi<String> saveRole(AdminRole adminRole) {
        if (adminRoleDao.saveInfo(adminRole)>0){
            return ResApi.getSuccess();
        }
        return ResApi.getError();
    }

    @Override
    public AdminRole getOneById(Integer id) {
        return adminRoleDao.getAdminRoleByFid(id);
    }

    @CacheEvict(value = {"verifyRole", "verifyPermission"}, allEntries = true)
    @Override
    public ResApi<String> updateRole(Integer id, AdminRole adminRole) {
        adminRole.setFId(id);
        if (!adminRole.getFRolename().equals(adminRoleDao.getAdminRoleByFid(adminRole.getFId()).getFRolename())&&
                adminRoleDao.getAdminRoleByName(adminRole)!=null){
            return ResApi.getError(501, "该权限代号已存在，请重新提交。");
        }else{
            if (adminRoleDao.updateInfo(adminRole)>0){
                return ResApi.getSuccess();
            }
        }
        return ResApi.getError();
    }

    @CacheEvict(value = {"verifyRole", "verifyPermission"}, allEntries = true)
    @Override
    public ResApi<String> deleteRole(Integer id) {
        if (adminRolePermissionDao.deleteInfoByRid(id)>0&&adminRoleDao.deleteInfo(id)>0){
            return ResApi.getSuccess();
        }
        return ResApi.getError();
    }

    @Override
    public ResApi<Object> toPerm(Integer id) {
        Map<String, Object> map=new HashMap<>(8);
        AdminRole include=adminRoleDao.getAdminRoleByFid(id);
        List<AdminPermission> adminPermissions=adminPermissionDao.getAdminPermissionAll();
        List<AdminPermission> exclude=new ArrayList<>();
        for (AdminPermission adminPermission : adminPermissions) {
            int flag = 0;
            for (int j = 0; j < include.getAdminRolePermissions().size(); j++) {
                if (adminPermission.getFId().equals(include.getAdminRolePermissions().get(j).getAdminPermission().getFId())) {
                    flag++;
                    break;
                }
            }
            if (flag == 0) {
                exclude.add(adminPermission);
            }
        }
        map.put("include",include);
        map.put("exclude",exclude);
        map.put("includeSize",include.getAdminRolePermissions().size());
        map.put("excludeSize",exclude.size());
        return ResApi.getSuccess(map);
    }

    @CacheEvict(value = {"verifyRole", "verifyPermission"}, allEntries = true)
    @Override
    public ResApi<String> deletePerm(Integer[] pid, Integer rid) {
        int operationNum=0;
        Map<String,Integer> map=new HashMap<>(8);
        map.put("fRid",rid);
        for (Integer aPid : pid) {
            map.put("fPid", aPid);
            if (adminRolePermissionDao.deleteInfoByRidAndPid(map) > 0) {
                operationNum++;
            }
        }
        if (operationNum==pid.length){
            return ResApi.getSuccess();
        }else{
            return ResApi.getError(501, "删除失败，请刷新重试。");
        }
    }

    @CacheEvict(value = {"verifyRole", "verifyPermission"}, allEntries = true)
    @Override
    public ResApi<String> addPerm(Integer[] pid, Integer rid) {
        int operationNum=0;
        Map<String,Integer> map=new HashMap<>(8);
        map.put("fRid",rid);
        for (Integer aPid : pid) {
            map.put("fPid", aPid);
            if (adminRolePermissionDao.saveInfo(map) > 0) {
                operationNum++;
            }
        }
        if (operationNum==pid.length){
            return ResApi.getSuccess();
        }else{
            return ResApi.getError(501, "添加失败，请刷新重试。");
        }
    }
}
