package com.smxy.recipe.controller;

import com.smxy.recipe.config.template.PathController;
import com.smxy.recipe.entity.ProductActiveDiscount;
import com.smxy.recipe.service.ProductActiveDiscountService;
import com.smxy.recipe.utils.ResApi;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Demo ProductActiveDiscountController
 *
 * @author Yangyihui
 * @date 2018/12/18 0018 15:53
 */
@PathController("/merchant/productActiveDiscountController")
public class ProductActiveDiscountController {
    @Autowired
    private ProductActiveDiscountService productActiveDiscountService;

    @RequiresPermissions("product:insert")
    @ResponseBody
    @PostMapping("/uploadProductActiveDiscount/{pid}/{mid}")
    public ResApi<String> uploadProductActiveDiscount(ProductActiveDiscount productActiveDiscount, @PathVariable("pid") Integer fPid, @PathVariable("mid") Integer fMid) {
        System.out.println(productActiveDiscount.toString());
        return productActiveDiscountService.insertProductActiveDiscount(productActiveDiscount, fPid, fMid);
    }
}
