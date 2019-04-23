package com.smxy.recipe.dao;

import com.smxy.recipe.entity.ProductActiveDiscount;

import java.util.List;
import java.util.Map;

/**
 * Demo ProductActiveDiscountsDao
 *
 * @author Yangyihui
 * @date 2018/12/18 0018 15:51
 */
public interface ProductActiveDiscountDao {

    /**
     * 功能描述: 新增一个限时折扣活动
     * @param productActiveDiscount 限时折扣活动实体类
     * @return : java.lang.Integer
     * @author : yangyihui
     * @date : 2018/12/18 0018 16:16
     */
    Integer insertProductActiveDiscount(ProductActiveDiscount productActiveDiscount);

    /**
     * 功能描述: 通过商家ID查询所有限时折扣商品
     * @param map 需求数据结合（商家ID）
     * @return : java.util.List<com.smxy.recipe.entity.ProductActiveDiscount>
     * @author : yangyihui
     * @date : 2018/12/19 0019 16:56
     */
    List<ProductActiveDiscount> selectAllDiscountProductByMid(Map<String, Object> map);

    /**
     * 功能描述: 更新限时折扣活动信息
     * @param productActiveDiscount 限时折扣活动实体类
     * @return : java.lang.Integer
     * @author : yangyihui
     * @date : 2018/12/20 0020 10:31
     */
    Integer updateProductActiveDiscountById(ProductActiveDiscount productActiveDiscount);

    /**
     * 功能描述: 通过ID获取限时活动信息
     * @param fId 限时活动ID
     * @return : com.smxy.recipe.entity.ProductActiveDiscount
     * @author : yangyihui
     * @date : 2018/12/20 0020 10:59
     */
    ProductActiveDiscount selectProductActiveDiscountById(Integer fId);

    /**
     * 功能描述: 通过ID删除限时活动信息
     * @param fId 限时折扣活动ID
     * @return : java.lang.Integer
     * @author : yangyihui
     * @date : 2018/12/20 0020 20:33
     */
    Integer deleteProductActiveDiscountById(Integer fId);

    /**
     * 功能描述: 根据商品id 获取折扣活动信息
     * @param fPid 1
     * @return : java.util.List<com.smxy.recipe.entity.ProductActiveDiscount>
     * @author : yangyihui
     * @date : 2019/4/21 0021 11:10
     */
    ProductActiveDiscount selectProductActiveDiscountByPId(Integer fPid);

}
