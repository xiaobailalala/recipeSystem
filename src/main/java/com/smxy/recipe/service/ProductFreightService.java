package com.smxy.recipe.service;

import com.smxy.recipe.utils.ResApi;

/**
 * Demo ProductFreightService
 *
 * @author Yangyihui
 * @date 2018/11/22 0022 17:03
 */
public interface ProductFreightService {
    /**
     * 功能描述: 获取所有商品运费模板
     * @return ResApi 工具类
     * @auther yangyihui
     * @date 2018/11/22 0022 17:03
     */
    ResApi<Object> getAllProductFreight();
}
