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

public interface AdminUserService {

    ResApi<String> isAdminUser(String fAccount);

    ResApi<String> userLogin(AdminUser adminUser, HttpServletRequest request, boolean rememberMe);

    ResApi<Object> userList();

    ResApi<Object> userById(Integer id);

    ResApi<Object> editorInfo(HttpServletRequest request, MultipartFile multipartFile, Integer fId, AdminUser adminUser);

    ResApi<Object> editorPassword(Integer fId, AdminUser adminUser);

    ResApi<Object> editorEmail(Integer fId, AdminUser adminUser);

    ResApi<Object> isEmail(String fEmail);

    ResApi<Object> sendEmail(String toEmail, String title, String type, String account);

    ResApi<Object> resetPwd(Boolean isRe, Integer id, AdminUser adminUser, String prePassword);

    ResApi<Object> deleteInfo(Integer id);

    ResApi<Object> resetUserPwd(Integer id, AdminUser adminUser);

    ResApi<Object> toUserPerm(Integer id);

    ResApi<Object> deletePerm(Integer[] rid, Integer uid);

    ResApi<Object> addPerm(Integer[] rid, Integer uid);

    ResApi<Object> isAcc(String account);

    ResApi<Object> saveInfo(AdminUser adminUser);

//    ResApi<Map<String, Object>> getRoleAndPermission(Integer uid);

    List<AdminRole> verifyRole(Integer uid);

    List<AdminPermission> verifyPermission(Integer uid);

}
