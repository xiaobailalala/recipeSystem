package com.smxy.recipe.dao;

import com.smxy.recipe.entity.ProductActiveReduction;

/**
 * Demo ProductActiveReductionDao
 *
 * @author Yangyihui
 * @date 2018/12/18 0018 17:08
 */
public interface ProductActiveReductionDao {

    /**
     * 功能描述: 新增一个满减优惠活动
     * @param productActiveReduction 满减优惠活动实体类
     * @return : java.lang.Integer
     * @author : yangyihui
     * @date : 2018/12/18 0018 17:41
     */
    Integer insertProductActiveReduction(ProductActiveReduction productActiveReduction);
}
