/**
 * Copyright © 2018 eSunny Info. Developer Stu. All rights reserved.
 *
 * @Package:
 * @author: zpx
 * Build File @date: 2018/8/5 22:53
 * @Description TODO
 * @version 1.0
 */
package com.smxy.recipe.service.impl;

import com.smxy.recipe.dao.AdminRoleDao;
import com.smxy.recipe.dao.AdminUserDao;
import com.smxy.recipe.dao.AdminUserRoleDao;
import com.smxy.recipe.entity.*;
import com.smxy.recipe.realm.LoginType;
import com.smxy.recipe.realm.UserToken;
import com.smxy.recipe.service.AdminUserService;
import com.smxy.recipe.utils.ResApi;
import com.smxy.recipe.utils.ToolsApi;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Service("adminUserService")
public class AdminUserServiceImpl implements AdminUserService {

    private AdminUserDao adminUserDao;
    private ToolsApi toolsApi;
    private AdminRoleDao adminRoleDao;
    private AdminUserRoleDao adminUserRoleDao;

    @Autowired
    public AdminUserServiceImpl(AdminUserDao adminUserDao, ToolsApi toolsApi, AdminRoleDao adminRoleDao, AdminUserRoleDao adminUserRoleDao) {
        this.adminUserDao = adminUserDao;
        this.toolsApi = toolsApi;
        this.adminRoleDao = adminRoleDao;
        this.adminUserRoleDao = adminUserRoleDao;
    }

    private static final String ADMIN_LOGIN_TYPE = LoginType.ADMIN.toString();

    @Override
    public ResApi<String> isAdminUser(String fAccount) {
        if (adminUserDao.isAdminUser(fAccount)!=null){
            return ResApi.getError(501, "该账号已存在。");
        }else{
            return ResApi.getSuccess();
        }
    }

    @Override
    public ResApi<String> userLogin(AdminUser adminUser, HttpServletRequest request,boolean rememberMe) {
        Subject currentUser = SecurityUtils.getSubject();
        try {
            if (!currentUser.isAuthenticated()){
                UserToken token = new UserToken(adminUser.getFAccount(), adminUser.getFPassword(), ADMIN_LOGIN_TYPE);
                token.setRememberMe(rememberMe);
                try {
                    currentUser.login(token);
                    adminUser= (AdminUser) currentUser.getPrincipal();
                    SecurityUtils.getSubject().getSession().setAttribute("aduser", adminUser);
                }catch (UnknownAccountException ae){
                    return ResApi.getError(501,"该管理员账号不存在。");
                }catch (IncorrectCredentialsException ice){
                    return ResApi.getError(502, "您输入的密码不正确。");
                }
            }
            return ResApi.getSuccess();
        }catch (Exception e){
            return ResApi.getError();
        }
    }

    @Override
    public ResApi<Object> userList() {
        return ResApi.getSuccess(adminUserDao.getAdminUserAll());
    }

    @Override
    public ResApi<Object> userById(Integer id) {
        return ResApi.getSuccess(adminUserDao.getAdminUserByFid(id));
    }

    @Override
    public ResApi<Object> editorInfo(HttpServletRequest request,MultipartFile multipartFile,Integer id, AdminUser adminUser) {
        adminUser.setFId(id);
        if (multipartFile==null||multipartFile.getSize()==0){
            if (adminUserDao.updateInfoByFid(adminUser)>0){
                return new ResApi<>(200,"success",true);
            }else{
                return new ResApi<>(501,"failed",false);
            }
        }else{
            ToolsApi.multipartFileDeleteFile(adminUser.getFHead());
            if (ToolsApi.imgLimit(ToolsApi.suffixName(multipartFile.getOriginalFilename()))){
                String name=ToolsApi.multipartFileUploadFile(multipartFile,null);
                adminUser.setFHead(name);
                if (adminUserDao.updateInfoByFid(adminUser)>0){
                    request.getSession().setAttribute("aduser",adminUserDao.getAdminUserByFid(adminUser.getFId()));
                    return new ResApi<>(200,"success",true);
                }else{
                    return new ResApi<>(501,"failed",false);
                }
            }else{
                return new ResApi<>(502,"上传的头像格式不符合要求。",false);
            }
        }
    }

    @Override
    public ResApi<Object> editorPassword(Integer id,AdminUser adminUser) {
        adminUser.setFId(id);
        if (adminUserDao.updatePasswordByFid(adminUser)>0){
            return new ResApi<>(200,"success",true);
        }else{
            return new ResApi<>(501,"failed",false);
        }
    }

    @Override
    public ResApi<Object> editorEmail(Integer id,AdminUser adminUser) {
        adminUser.setFId(id);
        if (adminUserDao.updateEmailByFid(adminUser)>0){
            return new ResApi<>(200,"success",true);
        }else{
            return new ResApi<>(501,"failed",false);
        }
    }

    @Override
    public ResApi<Object> isEmail(String fEmail) {
        if (adminUserDao.isEmail(fEmail)>0){
            return new ResApi<>(401,"该邮箱已绑定其他账号。",false);
        }else{
            return new ResApi<>(200,"success",true);
        }
    }

    @Override
    public ResApi<Object> sendEmail(String toEmail, String title, String type, String account) {
        Random random = new Random();
        String code="";
        int codeCount = 6;
        for (int i=0;i<codeCount;i++) {
            code+=random.nextInt(10);
        }
        toolsApi.sendMail(ToolsApi.MAILTYPE_VERIFY,title,toEmail,account,code);
        return new ResApi<>(200,"success",code);
    }

    @Override
    public ResApi<Object> resetPwd(Boolean isRe, Integer id,AdminUser adminUser,String prePassword) {
        ResApi<Object> resApi;
        if (isRe){
            if (adminUserDao.getAdminUserByFid(id).getFPassword().equals(ToolsApi.entryptBySaltMd5(prePassword,adminUser.getFAccount()))){
                resApi=new ResApi<>(200,"success","success");
            }else{
                resApi=new ResApi<>(501,"您输入的密码不正确。","failed");
            }
        }else{
            adminUser.setFPassword(ToolsApi.entryptBySaltMd5(adminUser.getFPassword(),adminUser.getFAccount()));
            if (adminUserDao.updatePasswordByFid(adminUser)>0){
                resApi=new ResApi<>(200,"success","success");
            }else{
                resApi=new ResApi<>(200,"密码修改失败，请重试。","failed");
            }
        }
        return resApi;
    }

    @Override
    public ResApi<Object> deleteInfo(Integer id) {
        ResApi<Object> resApi;
        if (adminUserRoleDao.deleteInfoByUid(id)>0&&adminUserDao.deleteUser(id)>0){
            resApi=new ResApi<>(200,"success","success");
        }else{
            resApi=new ResApi<>(501,"删除失败。","failed");
        }
        return resApi;
    }

    @Override
    public ResApi<Object> resetUserPwd(Integer id,AdminUser adminUser) {
        adminUser.setFId(id);
        ResApi<Object> resApi;
        String newPwd= ToolsApi.randomPwd();
        toolsApi.sendMail(ToolsApi.MAILTYPE_RESET,"膳食膳房——Manage 管理员密码重置",adminUser.getFEmail(),adminUser.getFAccount(),newPwd);
        adminUser.setFPassword(ToolsApi.entryptBySaltMd5(newPwd,adminUser.getFAccount()));
        int res=adminUserDao.updatePasswordByFid(adminUser);
        if (res>0){
            resApi=new ResApi<>(200,"success","success");
        }else{
            resApi=new ResApi<>(500,"密码重置失败，请稍后重试。","failed");
        }
        return resApi;
    }

    @Override
    public ResApi<Object> toUserPerm(Integer id) {
        ResApi<Object> resApi=new ResApi<>();
        Map<String, Object> map=new HashMap<>(8);
        AdminUser include=adminUserDao.getAdminUserByFid(id);
        List<AdminRole> adminRoles=adminRoleDao.getInfoAll();
        List<AdminRole> exclude=new ArrayList<>();
        for (int i=0;i<adminRoles.size();i++){
            int flag=0;
            for (int j=0;j<include.getAdminUserRoles().size();j++){
                if (adminRoles.get(i).getFId().equals(include.getAdminUserRoles().get(j).getAdminRole().getFId())){
                    flag++;
                    break;
                }
            }
            if (flag==0){
                exclude.add(adminRoles.get(i));
            }
        }
        map.put("include",include);
        map.put("exclude",exclude);
        map.put("includeSize",include.getAdminUserRoles().size());
        map.put("excludeSize",exclude.size());
        resApi.setData(map);
        return resApi;
    }

    @CacheEvict(value = {"verifyRole", "verifyPermission"}, allEntries = true)
    @Override
    public ResApi<Object> deletePerm(Integer[] rid, Integer uid) {
        ResApi<Object> resApi;
        int operationNum=0;
        Map<String,Integer> map=new HashMap<>(8);
        map.put("fUid",uid);
        for (int i=0;i<rid.length;i++){
            map.put("fRid",rid[i]);
            if (adminUserRoleDao.deleteInfoByUidAndRid(map)>0){
                operationNum++;
            }
        }
        if (operationNum==rid.length){
            resApi=new ResApi<>(200,"success","success");
        }else{
            resApi=new ResApi<>(501,"删除失败，请刷新重试。","failed");
        }
        return resApi;
    }

    @CacheEvict(value = {"verifyRole", "verifyPermission"}, allEntries = true)
    @Override
    public ResApi<Object> addPerm(Integer[] rid, Integer uid) {
        ResApi<Object> resApi;
        int operationNum=0;
        Map<String,Integer> map=new HashMap<>(8);
        map.put("fUid",uid);
        for (int i=0;i<rid.length;i++){
            map.put("fRid",rid[i]);
            if (adminUserRoleDao.saveInfo(map)>0){
                operationNum++;
            }
        }
        if (operationNum==rid.length){
            resApi=new ResApi<>(200,"success","success");
        }else{
            resApi=new ResApi<>(501,"添加失败，请刷新重试。","failed");
        }
        return resApi;
    }

    @Override
    public ResApi<Object> isAcc(String account) {
        ResApi<Object> resApi;
        if (adminUserDao.isAdminUser(account)!=null){
            resApi=new ResApi<>(501,"该账号已存在，请重试。","failed");
        }else{
            resApi=new ResApi<>(200,"success","success");
        }
        return resApi;
    }

    @Override
    public ResApi<Object> saveInfo(AdminUser adminUser) {
        ResApi<Object> resApi=new ResApi<>(500,"系统  出错。","error");
        adminUser.setFPassword(ToolsApi.entryptBySaltMd5(adminUser.getFPassword(), adminUser.getFAccount()));
        if (adminUserDao.saveInfo(adminUser)>0){
            resApi=new ResApi<>(200,"success","success");
        }
        return resApi;
    }

    @Cacheable(value = "verifyRole",key = "#uid")
    @Override
    public List<AdminRole> verifyRole(Integer uid) {
        List<AdminRole> adminRoles = new ArrayList<>();
        for (AdminUserRole adminUserRole:adminUserRoleDao.getAdminUserRoleByFuid(uid)){
            adminRoles.add(adminUserRole.getAdminRole());
        }
        return adminRoles;
    }

    @Cacheable(value = "verifyPermission", key = "#uid")
    @Override
    public List<AdminPermission> verifyPermission(Integer uid) {
        List<AdminPermission> adminPermissions = new ArrayList<>();
        for (AdminUserRole adminUserRole:adminUserRoleDao.getAdminUserRoleByFuid(uid)){
            for (AdminRolePermission adminRolePermission:adminUserRole.getAdminRole().getAdminRolePermissions()){
                adminPermissions.add(adminRolePermission.getAdminPermission());
            }
        }
        return adminPermissions;
    }

}
