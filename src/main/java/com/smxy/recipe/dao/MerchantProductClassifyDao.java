package com.smxy.recipe.dao;

import com.smxy.recipe.entity.MerchantProductClassify;

import java.util.List;

/**
 * Demo MerhcantProductClassify
 *
 * @author Yangyihui
 * @date 2018/11/22 0022 09:08
 */

public interface MerchantProductClassifyDao{
    /**
     * 功能描述: 增加一条商品类型
     * @param merchantProductClassify 商品类型实体类
     * @return 数据库更新数
     * @auther yangyihui
     * @date 2018/11/22 0022 9:13
     */
    Integer saveProductClassify(MerchantProductClassify merchantProductClassify);
    /**
     * 功能描述: 根据ID删除一条商品型号
     * @param fId 商品型号类ID
     * @return 数据库更新数
     * @auther yangyihui
     * @date 2018/11/22 0022 9:14
     */
    Integer deleteProductClassify(Integer fId);

    /**
     * 功能描述: 根据商品类型实体类更新
     * @param merchantProductClassify 商品类型实体类
     * @return : java.lang.Integer
     * @author : yangyihui
     * @date : 2018/12/14 0014 19:30
     */
    Integer updateProductClassifyById(MerchantProductClassify merchantProductClassify);

    /**
     * 功能描述: 获取所有商品型号
     * @return 所有商品型号的集合
     * @auther yangyihui
     * @date 2018/11/22 0022 9:15
     */
    List<MerchantProductClassify> getAllProductClassify();

    /**
     * 功能描述: 通过ID获取商品类型
     * @param fId 商品类型ID
     * @return 商品类型实体类
     * @auther yangyihui
     * @date 2018/11/26 0026 8:17
     */
    MerchantProductClassify getProductClassifyById(Integer fId);
}
