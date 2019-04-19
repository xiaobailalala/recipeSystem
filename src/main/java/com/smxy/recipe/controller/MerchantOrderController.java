package com.smxy.recipe.controller;

import com.smxy.recipe.config.template.PathController;
import com.smxy.recipe.entity.MerchantOrder;
import com.smxy.recipe.service.MerchantOrderService;
import com.smxy.recipe.utils.ResApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Demo MerchantOrderController
 *
 * @author Yangyihui
 * @date 2019/4/6 0006 17:01
 */
@PathController("/merchant/merchantOrder")
public class MerchantOrderController {

    private final MerchantOrderService merchantOrderService;

    @Autowired
    public MerchantOrderController(MerchantOrderService merchantOrderService) {
        this.merchantOrderService = merchantOrderService;
    }

    @ResponseBody
    @PostMapping("/save")
    public ResApi<String> save(MerchantOrder merchantOrder) {
        return merchantOrderService.saveOrder(merchantOrder);
    }

    @ResponseBody
    @GetMapping("/getOrderById/{orderId}")
    public ResApi<Object> getOrderById(@PathVariable("orderId") Integer orderId) {
        return merchantOrderService.getOrderById(orderId);
    }

    @ResponseBody
    @GetMapping("/getOrderByUserId/{userId}")
    public ResApi<List<MerchantOrder>> getOrderByUserId(Integer userId) {
        return merchantOrderService.getAllOrderByUserId(userId);
    }

    @ResponseBody
    @GetMapping("/getOrderByIdAndType/{userId}")
    public ResApi<List<MerchantOrder>> getOrderByIdAndType(@PathVariable("userId") Integer userId, @RequestParam("fType") Integer fType) {
        return merchantOrderService.getAllOrderByUserIdAndType(userId, fType);
    }

    @ResponseBody
    @GetMapping("/getOrderByIdAndTypeAndState/{userId}")
    public ResApi<List<MerchantOrder>> getOrderByIdAndTypeAndState(@PathVariable("userId") Integer userId, @RequestParam("fState") Integer fState, @RequestParam("fType") Integer fType) {
        return merchantOrderService.getAllOrderByUserIdAndTypeAndState(userId, fState, fType);
    }
}
