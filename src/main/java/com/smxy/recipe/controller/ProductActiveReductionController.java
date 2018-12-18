package com.smxy.recipe.controller;

import com.smxy.recipe.config.template.PathController;
import com.smxy.recipe.entity.ProductActiveDiscount;
import com.smxy.recipe.entity.ProductActiveReduction;
import com.smxy.recipe.service.ProductActiveDiscountService;
import com.smxy.recipe.service.ProductActiveReductionService;
import com.smxy.recipe.utils.ResApi;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Demo ProductActiveReductionController
 *
 * @author Yangyihui
 * @date 2018/12/18 0018 15:52
 */
@PathController("/merchant/productActiveReductionController")
public class ProductActiveReductionController {
    @Autowired
    private ProductActiveReductionService productActiveReductionService;

    @RequiresPermissions("product:insert")
    @ResponseBody
    @PostMapping("/uploadProductActiveReduction/{pid}/{mid}")
    public ResApi<String> uploadProductActiveReduction(ProductActiveReduction productActiveReduction, @RequestParam("fullmoney") Double[] fullMoney,
                                                       @RequestParam("reducemoney") Double[] reduceMoney, @PathVariable("pid") Integer fPid, @PathVariable("mid") Integer fMid) {
        return productActiveReductionService.insertProductActiveReduction(productActiveReduction, fullMoney, reduceMoney, fPid, fMid);
    }
}
