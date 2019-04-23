package com.smxy.recipe.dao;

import com.smxy.recipe.entity.MerchantUserProduct;

import java.util.List;

/**
 * Demo MerchantUserProductDao
 *
 * @author Yangyihui
 * @date 2018/11/23 0023 18:39
 */
public interface MerchantUserProductDao {
    /**
     * 功能描述: 保存一条商品商家信息
     * @param merchantUserProduct 商家商品实体类
     * @return 数据库更新数
     * @auther yangyihui
     * @date 2018/11/23 0023 18:44
     */
    Integer saveMerchantUserProductInfo(MerchantUserProduct merchantUserProduct);

    /**
     * 功能描述: 根据商品ID删除数据
     * @param fPid 商品ID
     * @return 数据更新数
     * @auther yangyihui
     * @date 2018/11/26 0026 20:42
     */
    Integer deleteMerchantUserProductByFPid(Integer fPid);

    /**
     * 功能描述: 根据商家ID查找数据
     * @param fMid 商家ID
     * @return : java.util.List<java.lang.Integer>
     * @author : yangyihui
     * @date : 2018/12/12 0012 13:54
     */
    List<MerchantUserProduct> getMerchantUserProductByMid(Integer fMid);

    /**
     * 功能描述: 根据商品ID查找数据
     * @param fPid 商家ID
     * @return : java.util.List<java.lang.Integer>
     * @author : yangyihui
     * @date : 2018/12/12 0012 13:54
     */
    MerchantUserProduct getMerchantUserProductByPidBrief(Integer fPid);



}
