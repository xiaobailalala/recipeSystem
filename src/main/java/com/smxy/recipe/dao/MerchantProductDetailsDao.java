package com.smxy.recipe.dao;

import com.smxy.recipe.entity.MerchantProductDetails;

/**
 * Demo MerchantProductDetailsDao
 *
 * @author Yangyihui
 * @date 2018/11/23 0023 18:18
 */
public interface MerchantProductDetailsDao {
    /**
     * 功能描述: 保存一条商品实体类
     * @param merchantProductDetails 商品详情实体类
     * @return 数据库更新数
     * @auther yangyihui
     * @date 2018/11/26 0026 20:45
     */
    Integer saveProductDetailsInfo(MerchantProductDetails merchantProductDetails);

    /**
     * 功能描述:根据商品ID删除商品详情
     * @param fPid 商品ID
     * @return 数据库更新数
     * @auther yangyihui
     * @date 2018/11/26 0026 20:46
     */
    Integer deleteProductDetailsByFPid(Integer fPid);

}
