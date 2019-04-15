package com.smxy.recipe.controller.merchantapp;

import com.smxy.recipe.config.template.PathRestController;
import com.smxy.recipe.entity.MerchantOrder;
import com.smxy.recipe.service.MerchantOrderService;
import com.smxy.recipe.utils.ResApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Demo MerchantOrderMobController
 *
 * @author Yangyihui
 * @date 2019/4/6 0006 16:59
 */
@PathRestController("/merchantMob/merchantOrderMob")
public class MerchantOrderMobController {
    @Autowired
    private MerchantOrderService merchantOrderService;

    @PostMapping("/save")
    public ResApi<String> save(MerchantOrder merchantOrder) {
        return merchantOrderService.saveOrder(merchantOrder);
    }

    @GetMapping("/getOrderById/{orderId}")
    public ResApi<Object> getOrderById(@PathVariable("orderId") Integer orderId) {
        return merchantOrderService.getOrderById(orderId);
    }

    @GetMapping("/getOrderByUserId/{userId}")
    public ResApi<List<MerchantOrder>> getOrderByUserId(@PathVariable("userId") Integer userId) {
        return merchantOrderService.getAllOrderByUserId(userId);
    }

    @PostMapping("/getOrderByIdAndTypeAndState/{userId}")
    public ResApi<List<MerchantOrder>> getOrderByIdAndTypeAndState(@PathVariable("userId") Integer userId, @RequestParam("fState") Integer fState, @RequestParam("fType") Integer fType) {
        return merchantOrderService.getAllOrderByUserIdAndTypeAndState(userId, fState, fType);
    }

    @GetMapping("/getOrderByIdAndType/{userId}")
    public ResApi<List<MerchantOrder>> getOrderByIdAndType(@PathVariable("userId") Integer userId, @RequestParam("fType") Integer fType) {
        return merchantOrderService.getAllOrderByUserIdAndType(userId, fType);
    }

    @GetMapping("/getOrderByIdAndState/{userId}")
    public ResApi<List<MerchantOrder>> getOrderByIdAndState(@PathVariable("userId") Integer userId, @RequestParam("fState") Integer fState) {
        return merchantOrderService.getAllOrderByUserIdAndState(userId, fState);
    }


}
