package com.smxy.recipe.dao;

import com.smxy.recipe.entity.MerchantProductImage;

import java.util.List;

/**
 * Demo MerchantProductImageDao
 *
 * @author Yangyihui
 * @date 2018/11/20 0020 19:34
 */
public interface MerchantProductImageDao {
    /**
     * 功能描述: 保存商品及对应的图片信息
     *
     * @param merchantProductImage 商品图片-商品实体类
     * @return 数据库更新数
     * @auther yangyihui
     * @date 2018/11/24 0024 16:52
     */
    Integer saveProductImage(MerchantProductImage merchantProductImage);

    /**
     * 功能描述: 根据商品ID删除商品图片
     *
     * @param fPid 商品ID
     * @return 数据库更新数
     * @auther yangyihui
     * @date 2018/11/26 0026 20:38
     */
    Integer deleteProductImageByfPid(Integer fPid);

    /**
     * 功能描述: 根据ID删除商品图片
     * @param fId 商品图片ID
     * @return : java.lang.Integer
     * @author : yangyihui
     * @date : 2018/12/9 0009 15:11
     */
    Integer deleteProductImageByFId(Integer fId);

    /**
     * 功能描述: 根据商品ID查找商品图片
     *
     * @param fPid 商品ID
     * @return : java.util.List<com.smxy.recipe.entity.MerchantProductImage>
     * @author : yangyihui
     * @date : 2018/12/2 0002 18:10
     */
    List<MerchantProductImage> getProductByfPid(Integer fPid);

    /**
     * 功能描述: 通过商品ID返回对应所有ID
     *
     * @param fPid 商品ID
     * @return : java.lang.Integer[]
     * @author : yangyihui
     * @date : 2018/12/9 0009 14:50
     */
    List<Integer> getProductImageId(Integer fPid);
}
