package com.smxy.recipe.controller;

import com.smxy.recipe.config.template.PathController;
import com.smxy.recipe.service.ProductFreightService;
import com.smxy.recipe.utils.ResApi;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Demo MerchantProductFreightController
 *
 * @author Yangyihui
 * @date 2018/11/22 0022 17:06
 */
@PathController("/merchant/merchantProductFreight")
public class MerchantProductFreightController {
    @Autowired
    private ProductFreightService productFreightService;

    @RequiresPermissions("product:select")
    @ResponseBody
    @GetMapping("/getAllFreight")
    public ResApi<Object> getAllFreight(){
        return productFreightService.getAllProductFreight();
    }
}
