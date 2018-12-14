package com.smxy.recipe.controller.merchantapp;

import com.smxy.recipe.config.template.PathRestController;
import com.smxy.recipe.utils.ResApi;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Demo MerchantMobCommon
 *
 * @author Yangyihui
 * @date 2018/12/13 0013 09:16
 */
@PathRestController("/merchantMobCommon")
public class MerchantMobCommonController {

    @RequestMapping("/unauthorized")
    @ResponseBody
    public ResApi<String> unauthorized(){
        return new ResApi<>(405, "未授权！", "unauthorized");
    }
}
