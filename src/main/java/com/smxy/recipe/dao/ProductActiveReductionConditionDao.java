package com.smxy.recipe.dao;

import com.smxy.recipe.entity.ProductActiveReductionCondition;

import java.util.List;
import java.util.Map;

/**
 * Demo ProductAvtiveReductionConditionDao
 *
 * @author Yangyihui
 * @date 2018/12/20 0020 22:05
 */
public interface ProductActiveReductionConditionDao {

    /**
     * 功能描述: 插入一条活动选择项数据（只针对满减优惠活动）
     * @param productActiveReductionCondition 活动选择项（满多少，减多少）
     * @return : java.lang.Integer
     * @author : yangyihui
     * @date : 2018/12/20 0020 22:07
     */
    Integer insertProductActiveReductionCondition(ProductActiveReductionCondition productActiveReductionCondition);

    /**
     * 功能描述: 根据活动ID查询所有活动选项
     * @param fAId 活动Id
     * @return : java.util.List<com.smxy.recipe.entity.ProductActiveReductionCondition>
     * @author : yangyihui
     * @date : 2018/12/20 0020 22:22
     */
    List<ProductActiveReductionCondition> selectProductActiveReductionConditionByAId(Integer fAId);

    /**
     * 功能描述: 根据活动选项ID删除一条数据
     * @param fId 活动选项ID
     * @return : java.lang.Integer
     * @author : yangyihui
     * @date : 2018/12/20 0020 22:22
     */
    Integer deleteProductActiveReductionConditionById(Integer fId);

    /**
     * 功能描述: 根据活动ID删除满减活动选项信息
     * @param fAid 活动ID
     * @return : java.lang.Integer
     * @author : yangyihui
     * @date : 2018/12/21 0021 11:09
     */
    Integer deleteProductActiveReductionConditionByAId(Integer fAid);

    /**
     * 功能描述: 根据商品活动选项ID更新一条信息
     * @param productActiveReductionCondition 商品活动选项实体类
     * @return : java.lang.Integer
     * @author : yangyihui
     * @date : 2018/12/21 0021 21:18
     */
    Integer updateProductActiveReductionConditionById(ProductActiveReductionCondition productActiveReductionCondition);
}
