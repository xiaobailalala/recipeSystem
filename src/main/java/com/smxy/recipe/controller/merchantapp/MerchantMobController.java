package com.smxy.recipe.controller.merchantapp;

import com.smxy.recipe.config.template.PathRestController;
import com.smxy.recipe.service.MerchantUserService;
import com.smxy.recipe.utils.ResApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Demo MerchantMobController
 *
 * @author Yangyihui
 * @date 2019/4/14 0014 15:52
 */
@PathRestController("/merchantMob")
public class MerchantMobController {
    @Autowired
    private MerchantUserService merchantUserService;

    @GetMapping("/getIndexData/{userId}")
    public ResApi<String> getIndexData(@PathVariable("userId") Integer userId) {
        return merchantUserService.getIndexData(userId);
    }

}
