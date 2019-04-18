package com.smxy.recipe.controller;

import com.smxy.recipe.config.template.PathController;
import com.smxy.recipe.service.ProductActiveReductionConditionService;
import com.smxy.recipe.utils.ResApi;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Demo ProductActiveReductionConditionController
 *
 * @author Yangyihui
 * @date 2018/12/21 0021 16:46
 */
@PathController("/merchant/productActiveReductionCondition")
public class ProductActiveReductionConditionController {

    private final ProductActiveReductionConditionService productActiveReductionConditionService;

    @Autowired
    public ProductActiveReductionConditionController(ProductActiveReductionConditionService productActiveReductionConditionService) {
        this.productActiveReductionConditionService = productActiveReductionConditionService;
    }

    @RequiresPermissions("product:delete")
    @DeleteMapping("/deleteProductActiveReductionConditionById/{id}")
    @ResponseBody
    public ResApi<String> deleteProductActiveReductionConditionById(@PathVariable("id") Integer fId) {
        return productActiveReductionConditionService.deleteProductActiveReductionConditionById(fId);
    }
}
