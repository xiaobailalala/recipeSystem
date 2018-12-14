package com.smxy.recipe.dao;

import com.smxy.recipe.entity.MerchantProductDetails;

import java.util.List;

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
     * 功能描述: 更新一条商品详情
     * @param merchantProductDetails 商品详情实体类
     * @return : java.lang.Integer
     * @author : yangyihui
     * @date : 2018/12/9 0009 22:28
     */
    Integer updateProductDetailsInfo(MerchantProductDetails merchantProductDetails);

    /**
     * 功能描述:根据商品ID删除商品详情
     * @param fPid 商品ID
     * @return 数据库更新数
     * @auther yangyihui
     * @date 2018/11/26 0026 20:46
     */
    Integer deleteProductDetailsByFPid(Integer fPid);

    /**
     * 功能描述: 根据ID删除对应的商品详情
     * @param fId 商品详情ID
     * @return : java.lang.Integer
     * @author : yangyihui
     * @date : 2018/12/9 0009 22:23
     */
    Integer deleteProductDetailsByFId(Integer fId);

    /**
     * 功能描述: 根据商品ID获取商品详情
     * @param fPid 商品ID
     * @return : java.util.List<com.smxy.recipe.entity.MerchantProductDetails> 商品详情实体类集合
     * @author : yangyihui
     * @date : 2018/11/30 0030 21:28
     */
    List<MerchantProductDetails> getProductDetailsByPid(Integer fPid);

    /**
     * 功能描述: 根据商品ID查找所有商品详情ID
     * @param fPid 商品ID
     * @return : java.util.List<java.lang.Integer>
     * @author : yangyihui
     * @date : 2018/12/9 0009 22:21
     */
    List<Integer> getDetailsId(Integer fPid);

    /**
     * 功能描述: 根据ID查找对应的商品详情图片路径
     * @param fId 商品详情ID
     * @return : java.lang.String
     * @author : yangyihui
     * @date : 2018/12/9 0009 22:33
     */
    String getDetailImagePath(Integer fId);
}
