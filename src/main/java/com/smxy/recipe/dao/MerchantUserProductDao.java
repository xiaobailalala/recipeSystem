package com.smxy.recipe.dao;

import com.smxy.recipe.entity.MerchantUserProduct;

import java.util.Map;

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
}
