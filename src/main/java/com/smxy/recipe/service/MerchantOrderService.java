package com.smxy.recipe.service;

import com.smxy.recipe.entity.MerchantOrder;
import com.smxy.recipe.utils.ResApi;

import java.util.List;

/**
 * Demo MerchantOrder
 *
 * @author Yangyihui
 * @date 2019/4/9 0009 20:57
 */
public interface  MerchantOrderService {
    /**
     * 功能描述: 根据id获取订单信息
     * @param orderId 1
     * @return : com.smxy.recipe.utils.ResApi<java.lang.Object>
     * @author : yangyihui
     * @date : 2019/4/11 0011 22:26
     */
    ResApi<Object> getOrderById(Integer orderId);

    ResApi<List<MerchantOrder>> getAllOrderByUserId(Integer userId);

    ResApi<List<MerchantOrder>> getAllOrderByUserIdAndTypeAndState(Integer userId, Integer fState, Integer fType);

    ResApi<List<MerchantOrder>> getAllOrderByUserIdAndType(Integer userId, Integer fType);

    ResApi<String> updateOrderStateById(Integer orderId);

    ResApi<String> deleterOrderById(Integer orderId);

    ResApi<String> saveOrder(MerchantOrder merchantOrder);

    ResApi<List<MerchantOrder>> getAllOrderByUserIdAndState(Integer userId, Integer fState);
}
