package com.smxy.recipe.dao;

import com.smxy.recipe.entity.ProductActiveReduction;

import java.util.List;

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

    /**
     * 功能描述: 根据活动ID删除一条数据
     * @param fId 活动ID
     * @return : java.lang.Integer
     * @author : yangyihui
     * @date : 2018/12/20 0020 22:35
     */
    Integer deleteProductActiveReduction(Integer fId);

    /**
     * 功能描述: 更新满减活动信息
     * @param productActiveReduction 商品满减活动信息实体类
     * @return : java.lang.Integer
     * @author : yangyihui
     * @date : 2018/12/21 0021 21:26
     */
    Integer updateProductActiveReductionById(ProductActiveReduction productActiveReduction);

    /**
     * 功能描述: 根据活动ID查找满减优惠活动
     * @param fId 活动ID
     * @return : com.smxy.recipe.entity.ProductActiveReduction
     * @author : yangyihui
     * @date : 2018/12/20 0020 22:41
     */
    ProductActiveReduction selectProductActiveReductionById(Integer fId);

    /**
     * 功能描述: 根据商家ID查找所有满减优惠活动
     * @param fMid 商家ID
     * @return : java.util.List<com.smxy.recipe.entity.ProductActiveReduction>
     * @author : yangyihui
     * @date : 2018/12/21 0021 10:55
     */
    List<ProductActiveReduction> selectProductActiveReductionByMid(Integer fMid);


}
