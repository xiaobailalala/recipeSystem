package com.smxy.recipe.dao;

import com.smxy.recipe.entity.MerchantProductImage;

/**
 * Demo MerchantProductImageDao
 *
 * @author Yangyihui
 * @date 2018/11/20 0020 19:34
 */
public interface MerchantProductImageDao {
    /**
     * 功能描述: 保存商品及对应的图片信息
     * @param merchantProductImage 商品图片-商品实体类
     * @return 数据库更新数
     * @auther yangyihui
     * @date 2018/11/24 0024 16:52
     */
    Integer saveProductImage(MerchantProductImage merchantProductImage);

    /**
     * 功能描述: 根据商品ID删除商品图片
     * @param fPid 商品ID
     * @return 数据库更新数
     * @auther yangyihui
     * @date 2018/11/26 0026 20:38
     */
    Integer deleteProductImageByfPid(Integer fPid);
}
