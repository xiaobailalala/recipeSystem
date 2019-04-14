package com.smxy.recipe.service.impl;

import com.smxy.recipe.dao.MerchantOrderDao;
import com.smxy.recipe.entity.MerchantOrder;
import com.smxy.recipe.service.MerchantOrderService;
import com.smxy.recipe.utils.ResApi;
import com.smxy.recipe.utils.ToolsApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Demo MerchantOrderServiceImpl
 *
 * @author Yangyihui
 * @date 2019/4/9 0009 20:58
 */
@Service
public class MerchantOrderServiceImpl implements MerchantOrderService {
    private static final Logger logger = LoggerFactory.getLogger(MerchantOrderService.class);

    @Autowired
    private MerchantOrderDao merchantOrderDao;

    @Override
    public ResApi<Object> getOrderById(Integer orderId) {
        MerchantOrder orderByStateAndId = merchantOrderDao.getOrderById(orderId);
        if (orderByStateAndId == null) {
            return new ResApi<>(500, "获取订单信息失败", null);
        }
        return ResApi.getSuccess(orderByStateAndId);
    }

    @Override
    public ResApi<List<MerchantOrder>> getAllOrderByUserId(Integer userId) {
        List<MerchantOrder> merchantOrderList = merchantOrderDao.getOrderByUserId(userId);
        return new ResApi<>(200, "success", merchantOrderList);
    }

    @Override
    public ResApi<List<MerchantOrder>> getAllOrderByUserIdAndTypeAndState(Integer userId, Integer fState, Integer fType) {
        List<MerchantOrder> merchantOrderList = merchantOrderDao.getOrderByUserIdAndStateAndType(userId, fState, fType);
        return new ResApi<>(200, "success", merchantOrderList);
    }

    @Override
    public ResApi<List<MerchantOrder>> getAllOrderByUserIdAndType(Integer userId, Integer fType) {
        List<MerchantOrder> merchantOrderList = merchantOrderDao.getOrderByTypeAndId(userId, fType);
        return new ResApi<>(200, "success", merchantOrderList);
    }

    @Override
    public ResApi<List<MerchantOrder>> getAllOrderByUserIdAndState(Integer userId, Integer fType) {
        List<MerchantOrder> merchantOrderList = merchantOrderDao.getOrderByStateAndId(userId, fType);
        return new ResApi<>(200, "success", merchantOrderList);
    }


    @Override
    public ResApi<String> updateOrderStateById(Integer orderId) {
        return null;
    }

    @Override
    public ResApi<String> deleterOrderById(Integer orderId) {
        return null;
    }

    @Override
    public ResApi<String> saveOrder(MerchantOrder merchantOrder) {
        String saltMd5 = ToolsApi.entryptBySaltMd5(System.currentTimeMillis() + merchantOrder.getFUserAccount(), merchantOrder.getFUserName());
        merchantOrder.setFOrderNumber(saltMd5);
        Integer saveOrderById = merchantOrderDao.saveOrderById(merchantOrder);
        if (saveOrderById < 1) {
            return ResApi.getError();
        }
        return ResApi.getSuccess();
    }
}
