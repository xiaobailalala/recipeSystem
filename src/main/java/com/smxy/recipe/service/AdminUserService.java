/**
 * Copyright © 2018 eSunny Info. Developer Stu. All rights reserved.
 *
 * @Package:
 * @author: zpx
 * Build File @date: 2018/8/5 22:52
 * @Description TODO
 * @version 1.0
 */
package com.smxy.recipe.service;

import com.smxy.recipe.entity.AdminPermission;
import com.smxy.recipe.entity.AdminRole;
import com.smxy.recipe.entity.AdminUser;
import com.smxy.recipe.utils.ResApi;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface AdminUserService {

    public ResApi<String> isAdminUser(String fAccount);

    public ResApi<String> userLogin(AdminUser adminUser, HttpServletRequest request, boolean rememberMe);

    public ResApi<Object> userList();

    public ResApi<Object> userById(Integer id);

    public ResApi<Object> editorInfo(HttpServletRequest request, MultipartFile multipartFile, Integer fId, AdminUser adminUser);

    public ResApi<Object> editorPassword(Integer fId, AdminUser adminUser);

    public ResApi<Object> editorEmail(Integer fId, AdminUser adminUser);

    public ResApi<Object> isEmail(String fEmail);

    public ResApi<Object> sendEmail(String toEmail, String title, String type, String account);

    public ResApi<Object> resetPwd(Boolean isRe, Integer id, AdminUser adminUser, String prePassword);

    public ResApi<Object> deleteInfo(Integer id);

    public ResApi<Object> resetUserPwd(Integer id, AdminUser adminUser);

    public ResApi<Object> toUserPerm(Integer id);

    public ResApi<Object> deletePerm(Integer[] rid, Integer uid);

    public ResApi<Object> addPerm(Integer[] rid, Integer uid);

    public ResApi<Object> isAcc(String account);

    public ResApi<Object> saveInfo(AdminUser adminUser);

//    public ResApi<Map<String, Object>> getRoleAndPermission(Integer uid);

    public List<AdminRole> verifyRole(Integer uid);

    public List<AdminPermission> verifyPermission(Integer uid);

}
