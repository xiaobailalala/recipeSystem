
package com.smxy.recipe.dao;

import com.smxy.recipe.entity.MerchantUser;

import java.util.Map;

/**
 * Demo MerchantUserDao
 * @auther yangyihui
 * @date 2018/11/17 0017 21:33
 */
public interface MerchantUserDao {

    /**
     * 功能描述: 根据商家ID查询数据
     * @param fId 商家ID
     * @return : com.smxy.recipe.entity.MerchantUser
     * @author : yangyihui
     * @date : 2018/12/12 0012 13:59
     */
    MerchantUser getMerchantUserById(Integer fId);

    /**
     * 功能描述:根据一个商家用户名查找一个商家
     *
     * @param fAccount 商家用户名（手机号）
     * @return 一个商家用户类
     * @auther: yangyihui
     * @date: 2018/11/12 0012 20:25
     */
    MerchantUser isMerchantUser(String fAccount);

    /**
     * 功能描述:
     *
     * @param fAccount 商家用户名
     * @return 数据库更新数
     * @auther: yangyihui
     * @date: 2018/11/12 0012 20:29
     */
    Integer isAccount(String fAccount);

    /**
     * 功能描述: 根据用户名和密码查找商家
     *
     * @param merchantUser 商家用户信息类
     * @return 数据库更新数
     * @auther: yangyihui
     * @date: 2018/11/12 0012 20:31
     */
    Integer isLogin(MerchantUser merchantUser);

    /**
     * 功能描述: 删除一个商家用户jhaDGSFKL;'
     *
     * @param fId 要删除的商家ID
     * @return 数据库更新字段
     * @auther: yangyihui
     * @date: 2018/11/12 0012 20:59
     */
    Integer deleteUser(Integer fId);

    /**
     * 功能描述: 注册商家用户
     * @param merchantUser 商家用户信息类
     * @return 数据库更新数
     * @auther: yangyihui
     * @date: 2018/11/12 0012 21:00
     */
    Integer saveUserInfo(MerchantUser merchantUser);

    /**
     * 功能描述:通过用户名获取商家id
     * @param fAccount 商家用户名
     * @return 数据库更新数
     * @auther: yangyihui
     * @date: 2018/11/14 0014 8:23
     */
    Integer getMerchantIdByAccount(String fAccount);

    /**
     * 功能描述: 更新商家用户的店铺名
     * @param merchantUser 商家用户实体类
     * @return
     * @auther yangyihui
     * @date 2018/11/19 0019 20:28
     */
    Integer updateMerchantUserCoverAndShopName(MerchantUser merchantUser);

    /**
     * 功能描述: 根据商家ID更改商家头像
     * @param map ID及头像路径集合
     * @return : java.lang.Integer
     * @author : yangyihui
     * @date : 2018/12/14 0014 13:27
     */
    Integer updateUserCoverById(Map<String, Object> map);

    /**
     * 功能描述: 更新商家用户信息
     * @param merchantUser 商家用户信息类
     * @return : java.lang.Integer
     * @author : yangyihui
     * @date : 2018/12/14 0014 16:23
     */
    Integer updateMerchantUserInfo(MerchantUser merchantUser);

    /**
     * 功能描述: 根据ID更新用户密码
     * @param map 信息集合
     * @return : java.lang.Integer
     * @author : yangyihui
     * @date : 2018/12/14 0014 16:31
     */
    Integer updateMerchantUserPasswordById(Map<String, Object> map);

    /**
     * 功能描述: 根据手机号查找用户
     * @param account 1
     * @return : com.smxy.recipe.entity.MerchantUser
     * @author : yangyihui
     * @date : 2019/4/15 14:53
     */
    MerchantUser getMerchantUserByAccount(String account);

}
