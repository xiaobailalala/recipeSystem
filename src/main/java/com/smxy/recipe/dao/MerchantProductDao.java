package com.smxy.recipe.dao;

import com.smxy.recipe.entity.MerchantProduct;

import java.util.List;

/**
 * Demo merchantProductDao
 *
 * @author Yangyihui
 * @date 2018/11/18 0018 10:26
 */
public interface MerchantProductDao {
    /**
     * 功能描述: 获取所有商品
     * @param 无
     * @return 所有商品集合
     * @auther yangyihui
     * @date 2018/11/20 0020 10:57
     */
    List<MerchantProduct> getAllProduct();

    Integer getProductById(Integer fId);

    /**
     * 功能描述: 添加商品
     * @param merchantProduct 商品实体类
     * @return 数据库更新数
     * @auther yangyihui
     * @date 2018/11/20 0020 15:02
     */
    Integer saveProductInfo(MerchantProduct merchantProduct);

}
