package com.smxy.recipe.service;

import com.smxy.recipe.entity.ProductActiveDiscount;
import com.smxy.recipe.utils.ResApi;

/**
 * Demo ProductActiveDiscountService
 *
 * @author Yangyihui
 * @date 2018/12/18 0018 16:10
 */
public interface ProductActiveDiscountService {
    /**
     * 功能描述: 向商品添加限时折扣活动
     * @param productActiveDiscount 限时活动实体类
     * @param fPid 商品ID
     * @param fMid 商家ID
     * @return : com.smxy.recipe.utils.ResApi<java.lang.String>
     * @author : yangyihui
     * @date : 2018/12/18 0018 15:44
     */
    ResApi<String> insertProductActiveDiscount(ProductActiveDiscount productActiveDiscount, Integer fPid, Integer fMid);

}
