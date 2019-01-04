package com.smxy.recipe.service;

import com.smxy.recipe.utils.ResApi;

/**
 * Demo ProductActiveReductionConditionService
 *
 * @author Yangyihui
 * @date 2018/12/21 0021 16:49
 */
public interface ProductActiveReductionConditionService {

    /**
     * 功能描述: 根据满减活动选项ID删除满减活动选项
     * @param fId 满减活动选项ID
     * @return : com.smxy.recipe.utils.ResApi<java.lang.String>
     * @author : yangyihui
     * @date : 2018/12/21 0021 16:55
     */
    ResApi<String> deleteProductActiveReductionConditionById(Integer fId);
}
