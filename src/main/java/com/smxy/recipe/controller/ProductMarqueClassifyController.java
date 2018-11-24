package com.smxy.recipe.controller;

import com.smxy.recipe.config.template.PathController;
import com.smxy.recipe.service.ProductMarqueClassifyService;
import com.smxy.recipe.utils.ResApi;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Demo ProductMarqueClassifyController
 *
 * @author Yangyihui
 * @date 2018/11/22 0022 11:37
 */
@PathController("/merchant/productMarqueClassify")
public class ProductMarqueClassifyController {
    @Autowired
    private ProductMarqueClassifyService productMarqueClassifyService;

    @RequiresPermissions("product:select")
    @GetMapping("/getAllMarqueClassify")
    @ResponseBody
    public ResApi<Object> getAllMarqueClassify(){
        return productMarqueClassifyService.getAllMarqueClassify();
    }

}
