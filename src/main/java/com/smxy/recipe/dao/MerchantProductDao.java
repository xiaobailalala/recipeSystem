package com.smxy.recipe.dao;

import com.smxy.recipe.entity.MerchantProduct;
import org.apache.ibatis.annotations.Param;

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
     * @return 所有商品集合
     * @auther yangyihui
     * @date 2018/11/20 0020 10:57
     */
    List<MerchantProduct> getAllProduct();

    /**
     * 功能描述: 通过ID获取商品
     * @param fId 商品ID
     * @return
     * @auther yangyihui
     * @date 2018/11/23 0023 19:56
     */
    MerchantProduct getProductById(Integer fId);

    /**
     * 功能描述: 添加商品
     * @param merchantProduct 商品实体类
     * @return 数据库更新数
     * @auther yangyihui
     * @date 2018/11/20 0020 15:02
     */
    Integer saveProductInfo(MerchantProduct merchantProduct);

    /**
     * 功能描述: 根据ID删除商品
     * @param fId 商品ID
     * @return 数据库更新数
     * @auther yangyihui
     * @date 2018/11/26 0026 11:39
     */
    Integer deleteProductByFId(Integer fId);

    /**
     * 功能描述: 更新商品状态信息
     * @param fId  商品ID
     * @param fState 商品更新状态
     * @return 数据库更新数
     * @auther yangyihui
     * @date 2018/11/26 0026 15:08
     */
    Integer updateProductStatusById(@Param(value = "fId") Integer fId,@Param(value = "fState") String fState);

}