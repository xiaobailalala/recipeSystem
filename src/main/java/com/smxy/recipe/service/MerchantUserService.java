package com.smxy.recipe.service;

import com.smxy.recipe.entity.AdminPermission;
import com.smxy.recipe.entity.AdminRole;
import com.smxy.recipe.entity.MerchantChat;
import com.smxy.recipe.entity.MerchantUser;
import com.smxy.recipe.utils.ResApi;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Demo MerchantUserService
 *
 * @author Yangyihui
 * @date 2018/11/12 0012 21:30
 */
public interface MerchantUserService {
    /**
     * 功能描述: 商家用户登陆
     * @param merchantUser 商家用户信息类
     * @param request 请求域对象
     * @return ResApi值
     * @auther: yangyihui
     * @date: 2018/11/12 0012 21:33
     */
    ResApi<String> userLogin(MerchantUser merchantUser, HttpServletRequest request);

    /**
     * 功能描述: 根据商家ID更改商家头像
     * @param editorImage 头像文件
     * @param fId 商家ID
     * @return : com.smxy.recipe.utils.ResApi<java.lang.String>
     * @author : yangyihui
     * @date : 2018/12/14 0014 13:37
     */
    ResApi<String> editorUserCoverById(MultipartFile editorImage, Integer fId, HttpServletRequest request);

    /**
     * 功能描述: 商家用户手机登陆
     * @param merchantUser 商家用户信息类
     * @param request 请求域对象
     * @return : com.smxy.recipe.utils.ResApi<java.lang.Object>
     * @author : yangyihui
     * @date : 2018/12/13 0013 9:36
     */
    ResApi<Object> mobUserLogin(MerchantUser merchantUser, HttpServletRequest request);

    /**
     * 功能描述: 商家用户注册
     * @param merchantUser 商家用户信息类
     * @param request 请求域数据
     * @return : com.smxy.recipe.utils.ResApi<java.lang.String>
     * @author : yangyihui
     * @date : 2018/12/10 0010 14:53
     */
    ResApi<String> userRegister(MerchantUser merchantUser,HttpServletRequest request);

    /**
     * 功能描述: 验证角色
     * @param mid 商家角色ID
     * @return AdminRole 集合
     * @auther: yangyihui
     * @date: 2018/11/17 0017 20:36
     */
    List<AdminRole> verifyRole(Integer mid);


    /**
     * 功能描述: 通过商家ID查询所有权限
     * @param mid 商家ID
     * @return : java.util.List<com.smxy.recipe.entity.AdminPermission>
     * @author : yangyihui
     * @date : 2018/12/10 0010 15:09
     */
    List<AdminPermission> verifyPermission(Integer mid);

    /**
     * 功能描述: 根据商家ID更新商家用户信息
     * @param merchantUser 商家用户信息类
     * @param fId 商家ID
     * @return : com.smxy.recipe.utils.ResApi<java.lang.String>
     * @author : yangyihui
     * @date : 2018/12/14 0014 16:22
     */
    ResApi<String> editorUserDetails(MerchantUser merchantUser, Integer fId);

    /**
     * 功能描述: 根据用户ID更新用户密码
     * @param fPassword 用户密码
     * @param oldPassword 用户原密码
     * @param fId 用户ID
     * @return : com.smxy.recipe.utils.ResApi<java.lang.String>
     * @author : yangyihui
     * @date : 2018/12/14 0014 16:34
     */
    ResApi<String> editorUserPassword(String fPassword, String oldPassword, Integer fId);

    ResApi<String> getIndexData(Integer userId);

    ResApi<String> toMerchantSwitch(MultipartFile file, MerchantChat merchantChat);

    ResApi<Object> getMerchantChatMessage(MerchantChat merchantChat);

    ResApi<String> changeChatRead(MerchantChat merchantChat);
}
