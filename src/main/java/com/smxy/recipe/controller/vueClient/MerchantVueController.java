package com.smxy.recipe.controller.vueClient;

import com.alibaba.fastjson.JSONObject;
import com.smxy.recipe.config.template.PathRestController;
import com.smxy.recipe.dao.MerchantProductDao;
import com.smxy.recipe.entity.MerchantProductClassify;
import com.smxy.recipe.service.MerchantProductService;
import com.smxy.recipe.utils.ResApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * Demo MerchantVueController
 *
 * @author Yangyihui
 * @date 2019/4/20 0020 01:41
 */
@PathRestController("/merchantVue")
public class MerchantVueController {

    @Autowired
    private MerchantProductService merchantProductService;

    @GetMapping("/getProductByclaid/{claid}/{type}")
    ResApi<Object> getProductByclaid(@PathVariable("claid") Integer claid, @PathVariable("type") String type, JSONObject params) {
        return merchantProductService.getProductByClaid(claid, type, params);
    }
}
