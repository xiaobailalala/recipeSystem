package com.smxy.recipe.service;

import com.smxy.recipe.entity.ProductActiveReduction;
import com.smxy.recipe.utils.ResApi;

/**
 * Demo ProductActiveReductionService
 *
 * @author Yangyihui
 * @date 2018/12/18 0018 16:47
 */
public interface ProductActiveReductionService {

    /**
     * 功能描述: 向商品添加满减优惠活动
     * @param productActiveReduction 满减活动实体类
     * @param fullMoney 满足金额
     * @param reduceMoney 减少金额
     * @param fPid 商品ID
     * @param fMid 商家ID
     * @return : com.smxy.recipe.utils.ResApi<java.lang.String>
     * @author : yangyihui
     * @date : 2018/12/18 0018 20:38
     */
    ResApi<String> insertProductActiveReduction(ProductActiveReduction productActiveReduction, Double[] fullMoney, Double[] reduceMoney, Integer fPid, Integer fMid);
}
