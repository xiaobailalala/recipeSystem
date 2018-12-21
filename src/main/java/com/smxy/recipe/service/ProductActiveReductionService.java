package com.smxy.recipe.service;

import com.smxy.recipe.entity.ProductActiveReduction;
import com.smxy.recipe.utils.ResApi;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Demo ProductActiveReductionService
 *
 * @author Yangyihui
 * @date 2018/12/18 0018 16:47
 */
public interface ProductActiveReductionService {

    /**
     * 功能描述: 向商品添加满减优惠活动
     *
     * @param productActiveReduction 满减活动实体类
     * @param fullMoney              满足金额
     * @param reduceMoney            减少金额
     * @param fPid                   商品ID
     * @param fMid                   商家ID
     * @return : com.smxy.recipe.utils.ResApi<java.lang.String>
     * @author : yangyihui
     * @date : 2018/12/18 0018 20:38
     */
    ResApi<String> insertProductActiveReduction(ProductActiveReduction productActiveReduction, Double[] fullMoney, Double[] reduceMoney, Integer fPid, Integer fMid);

    /**
     * 功能描述: 根据商家ID获取所有满减活动信息
     *
     * @param fMid               商家ID
     * @param httpServletRequest 2
     * @return : java.util.Map<java.lang.String,java.lang.Object>
     * @author : yangyihui
     * @date : 2018/12/21 0021 11:07
     */
    Map<String, Object> getProductActiveReductionListByMid(Integer fMid, HttpServletRequest httpServletRequest);

    /**
     * 功能描述: 通过ID获取满减活动信息
     *
     * @param fId 满减活动ID
     * @return : com.smxy.recipe.utils.ResApi<java.lang.Object>
     * @author : yangyihui
     * @date : 2018/12/21 0021 11:11
     */
    ResApi<Object> getProductActiveDiscountById(Integer fId);

    /**
     * 功能描述: 根据ID删除满减优惠活动
     *
     * @param fId 满减活动ID
     * @return : com.smxy.recipe.utils.ResApi<java.lang.String>
     * @author : yangyihui
     * @date : 2018/12/21 0021 11:08
     */
    ResApi<String> deleteProductActiveReductionById(Integer fId);

    /**
     * 功能描述: 根据满减优惠活动ID更新信息
     * @param fId 满减优惠活动ID
     * @param productActiveReduction 满减活动实体类
     * @param newFullMoney 新加入的满金额数组
     * @param newReduceMoney 新加入的减金额数组
     * @param fullMoney 更新的满金额数组
     * @param reduceMoney 更新的减金额数组
     * @return : com.smxy.recipe.utils.ResApi<java.lang.String>
     * @author : yangyihui
     * @date : 2018/12/21 0021 20:52
     */
    ResApi<String> editorProductActiveReductionById(Integer fId, ProductActiveReduction productActiveReduction, Integer[] conditionId, Double[] newFullMoney, Double[] newReduceMoney, Double[] fullMoney, Double[] reduceMoney);
}
