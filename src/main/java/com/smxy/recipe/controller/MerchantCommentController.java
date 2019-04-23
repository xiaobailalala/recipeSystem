package com.smxy.recipe.controller;

import com.alibaba.fastjson.JSONObject;
import com.smxy.recipe.config.template.PathController;
import com.smxy.recipe.service.MerchantCommentService;
import com.smxy.recipe.utils.ResApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Demo MerchantCommentController
 *
 * @author Yangyihui
 * @date 2019/4/22 0022 23:12
 */
@PathController("/merchantCommon/merchantComment")
public class MerchantCommentController {

    @Autowired
    private MerchantCommentService merchantCommentService;

    @PostMapping("/saveMerchantComment/{fMid}/{fPid}/{fUid}/{fId}")
    @ResponseBody
    public ResApi<String> saveMerchantComment(@PathVariable("fMid") Integer fMid, @PathVariable("fUid") Integer fUid, @PathVariable("fId") Integer fId, @PathVariable("fPid") Integer fPid, @RequestBody JSONObject params) {
//        params = params.getJSONObject("params");
        return merchantCommentService.saveMerchantComment(fMid, fUid, fId, fPid, params);
    }

    @PostMapping("/listMerchantComment/{fMid}/{fState}")
    @ResponseBody
    public ResApi<Object> listMerchantCommentByMidAndState(@PathVariable("fMid")Integer fMid, @PathVariable("fState") Integer fState) {
        return merchantCommentService.listMerchantCommentByMidAndState(fMid, fState);
    }

    @PostMapping("/listMerchantCommentByPid/{fPid}/{fState}")
    @ResponseBody
    public ResApi<Object> listMerchantCommentByPidAndState(@PathVariable("fPid")Integer fPid, @PathVariable("fState") Integer fState) {
        return merchantCommentService.listMerchantCommentByPidAndState(fPid, fState);
    }

}
