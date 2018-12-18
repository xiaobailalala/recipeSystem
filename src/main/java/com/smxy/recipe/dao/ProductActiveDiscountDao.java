package com.smxy.recipe.dao;

import com.smxy.recipe.entity.ProductActiveDiscount;

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

}
