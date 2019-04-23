package com.smxy.recipe.service;

import com.alibaba.fastjson.JSONObject;
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
     * @param request
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

    /**
     * 功能描述: 根据userId 获取主页数据
     * @param userId 1
     * @return
     * @author : yangyihui
     * @date : 2019/4/15 14:46
     */
    ResApi<Object> getIndexData(Integer userId);

    ResApi<String> toMerchantSwitch(MultipartFile file, MerchantChat merchantChat);

    ResApi<Object> getMerchantChatMessage(MerchantChat merchantChat);

    ResApi<String> changeChatRead(MerchantChat merchantChat);

    /**
     * 功能描述: 忘记密码
     * @param account 1
     * @param password 2
     * @return : com.smxy.recipe.utils.ResApi<java.lang.String>
     * @author : yangyihui
     * @date : 2019/4/15 14:49
     */
    ResApi<String> forgetPassword(String account, String password);

    /**
     * 功能描述: 更新店铺名
     * @param shopName 1
     * @param userId
     * @return : com.smxy.recipe.utils.ResApi<java.lang.String>
     * @author : yangyihui
     * @date : 2019/4/15 15:59
     */
    ResApi<String> editorShopName(Integer userId, String shopName);

    /**
     * 功能描述: 更新店铺名
     * @param shopSign 1
     * @param userId
     * @return : com.smxy.recipe.utils.ResApi<java.lang.String>
     * @author : yangyihui
     * @date : 2019/4/15 15:59
     */
    ResApi<String> editorShopSign(Integer userId, String shopSign);

    /**
     * 功能描述: 更新店铺名
     * @param shopAddress 1
     * @param userId
     * @return : com.smxy.recipe.utils.ResApi<java.lang.String>
     * @author : yangyihui
     * @date : 2019/4/15 15:59
     */
    ResApi<String> editorShopAddress(Integer userId, String shopAddress);

    /**
     * 功能描述: 更新店铺名
     * @param shopAddress 1
     * @param userId
     * @return : com.smxy.recipe.utils.ResApi<java.lang.String>
     * @author : yangyihui
     * @date : 2019/4/15 15:59
     */
    ResApi<String> editorUserBirthday(Integer userId, String shopAddress);

    /**
     * 功能描述: 获取商家访问量总数
     * @param fMid 1
     * @author : yangyihui
     * @date : 2019/4/21 0021 17:57
     */
    ResApi<Object> getViewsCount(Integer fMid);

    /**
     * 功能描述: 获取商家一周内访问量/天数
     * @param fMid 1
     * @return : com.smxy.recipe.utils.ResApi<com.alibaba.fastjson.JSONObject>
     * @author : yangyihui
     * @date : 2019/4/21 0021 17:58
     */
    ResApi<JSONObject> getViewsCountByWeek(Integer fMid);

    /**
     * 功能描述: 更改用户手机号
     * @param userId 1
     * @param params 2
     * @return : com.smxy.recipe.utils.ResApi<java.lang.String>
     * @author : yangyihui
     * @date : 2019/4/21 0021 19:49
     */
    ResApi<String> editorUserAccount(Integer userId, JSONObject params);

    /**
     * 功能描述: 更新商家用户访问量
     * @return : void
     * @author : yangyihui
     * @date : 2019/4/21 0021 20:00
     */
    void saveMerchantViewsCount();

    /**
     * 功能描述: 获取商家用户数量
     * @param fMid 1
     * @return : com.smxy.recipe.utils.ResApi<java.lang.Object>
     * @author : yangyihui
     * @date : 2019/4/22 0022 22:13
     */
    ResApi<Object> getMerchantUserCount(Integer fMid);

    /**
     * 功能描述: 获取商家粉丝数
     * @param fMid 1
     * @return : com.smxy.recipe.utils.ResApi<java.lang.Object>
     * @author : yangyihui
     * @date : 2019/4/22 0022 22:22
     */
    ResApi<Object> getMerchantFansCount(Integer fMid);

    /**
     * 功能描述: 获取商家粉丝用户
     * @param fMid 1
     * @return : com.smxy.recipe.utils.ResApi<java.lang.Object>
     * @author : yangyihui
     * @date : 2019/4/22 0022 22:42
     */
    ResApi<Object> getMerchantFansUser(Integer fMid);

    /**
     * 功能描述: 保存商家提现金额
     * @param fMid 1
     * @return : com.smxy.recipe.utils.ResApi<java.lang.Object>
     * @author : yangyihui
     * @date : 2019/4/23 0023 10:56
     */
    ResApi<String> saveWithdrawMoney(Integer fMid, Double money);

    /** 获取商家收入
     * 功能描述:
     * @param fMid 1
     * @return : com.smxy.recipe.utils.ResApi<java.lang.Object>
     * @author : yangyihui
     * @date : 2019/4/23 0023 14:55
     */
    ResApi<Object> getUserWithdraw(Integer fMid);
}
