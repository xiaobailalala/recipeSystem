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
import com.smxy.recipe.entity.AdminUserRole;
import com.smxy.recipe.service.AdminRoleService;
import com.smxy.recipe.utils.ResApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("adminRoleService")
public class AdminRoleServiceImpl implements AdminRoleService {
    @Autowired
    private AdminRoleDao adminRoleDao;
    @Autowired
    private AdminRolePermissionDao adminRolePermissionDao;
    @Autowired
    private AdminPermissionDao adminPermissionDao;

    @Override
    public ResApi<Object> roleList() {
        return new ResApi<>(200,"success",adminRoleDao.getInfoAll());
    }

    @Override
    public ResApi<Object> isName(AdminRole adminRole) {
        ResApi<Object> resApi;
        if (adminRoleDao.getAdminRoleByName(adminRole)!=null){
            resApi=new ResApi<>(501,"该角色已添加，请勿重复添加。","failed");
        }else{
            resApi=new ResApi<>(200,"success","success");
        }
        return resApi;
    }

    @CacheEvict(value = {"verifyRole", "verifyPermission"}, allEntries = true)
    @Override
    public ResApi<Object> saveRole(AdminRole adminRole) {
        ResApi<Object> resApi=new ResApi<>(500,"系统出错。","error");
        if (adminRoleDao.saveInfo(adminRole)>0){
            resApi=new ResApi<>(200,"success","success");
        }
        return resApi;
    }

    @Override
    public AdminRole getOneById(Integer id) {
        return adminRoleDao.getAdminRoleByFid(id);
    }

    @CacheEvict(value = {"verifyRole", "verifyPermission"}, allEntries = true)
    @Override
    public ResApi<Object> updateRole(Integer id, AdminRole adminRole) {
        ResApi<Object> resApi=new ResApi<>(500,"系统出错了。","error");
        adminRole.setfId(id);
        if (!adminRole.getfRolename().equals(adminRoleDao.getAdminRoleByFid(adminRole.getfId()).getfRolename())&&
                adminRoleDao.getAdminRoleByName(adminRole)!=null){
            resApi=new ResApi<>(501,"该权限代号已存在，请重新提交。","failed");
        }else{
            if (adminRoleDao.updateInfo(adminRole)>0){
                resApi=new ResApi<>(200,"success","success");
            }
        }
        return resApi;
    }

    @CacheEvict(value = {"verifyRole", "verifyPermission"}, allEntries = true)
    @Override
    public ResApi<Object> deleteRole(Integer id) {
        ResApi<Object> resApi=new ResApi<>(500,"系统出错。","error");
        if (adminRolePermissionDao.deleteInfoByRid(id)>0&&adminRoleDao.deleteInfo(id)>0){
            resApi=new ResApi<>(200,"success","success");
        }
        return resApi;
    }

    @Override
    public ResApi<Object> toPerm(Integer id) {
        ResApi<Object> resApi=new ResApi<>();
        Map<String, Object> map=new HashMap<>();
        AdminRole include=adminRoleDao.getAdminRoleByFid(id);
        List<AdminPermission> adminPermissions=adminPermissionDao.getAdminPermissionAll();
        List<AdminPermission> exclude=new ArrayList<>();
        for (int i=0;i<adminPermissions.size();i++){
            int flag=0;
            for (int j=0;j<include.getAdminRolePermissions().size();j++){
                if (adminPermissions.get(i).getfId()==include.getAdminRolePermissions().get(j).getAdminPermission().getfId()){
                    flag++;
                    break;
                }
            }
            if (flag==0){
                exclude.add(adminPermissions.get(i));
            }
        }
        map.put("include",include);
        map.put("exclude",exclude);
        map.put("includeSize",include.getAdminRolePermissions().size());
        map.put("excludeSize",exclude.size());
        resApi.setData(map);
        return resApi;
    }

    @CacheEvict(value = {"verifyRole", "verifyPermission"}, allEntries = true)
    @Override
    public ResApi<Object> deletePerm(Integer[] pid, Integer rid) {
        ResApi<Object> resApi;
        int operationNum=0;
        Map<String,Integer> map=new HashMap<>();
        map.put("fRid",rid);
        for (int i=0;i<pid.length;i++){
            map.put("fPid",pid[i]);
            if (adminRolePermissionDao.deleteInfoByRidAndPid(map)>0){
                operationNum++;
            }
        }
        if (operationNum==pid.length){
            resApi=new ResApi<>(200,"success","success");
        }else{
            resApi=new ResApi<>(501,"删除失败，请刷新重试。","failed");
        }
        return resApi;
    }

    @CacheEvict(value = {"verifyRole", "verifyPermission"}, allEntries = true)
    @Override
    public ResApi<Object> addPerm(Integer[] pid, Integer rid) {
        ResApi<Object> resApi;
        int operationNum=0;
        Map<String,Integer> map=new HashMap<>();
        map.put("fRid",rid);
        for (int i=0;i<pid.length;i++){
            map.put("fPid",pid[i]);
            if (adminRolePermissionDao.saveInfo(map)>0){
                operationNum++;
            }
        }
        if (operationNum==pid.length){
            resApi=new ResApi<>(200,"success","success");
        }else{
            resApi=new ResApi<>(501,"添加失败，请刷新重试。","failed");
        }
        return resApi;
    }
}
