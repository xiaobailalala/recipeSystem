package com.smxy.recipe.controller.vueClient;

import com.alibaba.fastjson.JSONObject;
import com.smxy.recipe.config.template.PathRestController;
import com.smxy.recipe.service.MerchantProductService;
import com.smxy.recipe.utils.ResApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Demo MerchantVueController
 *
 * @author Yangyihui
 * @date 2019/4/20 0020 01:41
 */
@PathRestController("/common/merchantVue")
public class MerchantVueController {

    @Autowired
    private MerchantProductService merchantProductService;

    @PostMapping("/getProductByclaid/{claid}/{type}")
    ResApi<Object> getProductByclaid(@PathVariable("claid") Integer claid, @PathVariable("type") String type, @RequestBody JSONObject params) {
        return merchantProductService.getProductByClaid(claid, type, params.getJSONObject("params"));
    }

    @PostMapping("/getAllActiveProduct")
    ResApi<Object> getAllActiveProduct() {
        return merchantProductService.getAllActiveProduct();
    }

    @GetMapping("/getSixProduct")
    ResApi<Object> getSixProduct() {
        return merchantProductService.getSixProduct();
    }

    @GetMapping("/getFourProduct")
    ResApi<Object> getFourProduct() {
        return merchantProductService.getFourProduct();
    }

    @GetMapping("/getFourProductActive")
    ResApi<Object> getFourProductActive() {
        return merchantProductService.getFourProductActive();
    }

    @GetMapping("/getAllProductByMid/{fMid}")
    ResApi<Object> getAllProductByMid(@PathVariable("fMid") Integer fMid) {
        return merchantProductService.getAllProductByMid(fMid);
    }


}
