package com.smxy.recipe.service;

import com.smxy.recipe.utils.ResApi;

/**
 * Demo ProductMarqueClassifyService
 *
 * @author Yangyihui
 * @date 2018/11/22 0022 11:33
 */
public interface ProductMarqueClassifyService {
    /**
     * 功能描述: 获取所有商品型号分类
     * @return ResApi工具类
     * @auther yangyihui
     * @date 2018/11/22 0022 11:34
     */
    ResApi<Object> getAllMarqueClassify();
}
