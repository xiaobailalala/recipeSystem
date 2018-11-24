package com.smxy.recipe.service;

import com.smxy.recipe.utils.ResApi;

/**
 * Demo MerchantProductClassifyService
 *
 * @author Yangyihui
 * @date 2018/11/22 0022 10:58
 */
public interface MerchantProductClassifyService {
    /**
     * 功能描述:获取所有商品类型
     * @return ResApi 工具类
     * @auther yangyihui
     * @date 2018/11/22 0022 10:05
     */
    ResApi<Object> getAllProductClassify();
}
