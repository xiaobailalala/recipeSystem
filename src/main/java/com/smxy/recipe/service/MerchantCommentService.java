package com.smxy.recipe.service;

import com.alibaba.fastjson.JSONObject;
import com.smxy.recipe.utils.ResApi;

/**
 * Demo MerchantCommentService
 *
 * @author Yangyihui
 * @date 2019/4/22 0022 23:24
 */
public interface MerchantCommentService {
    ResApi<String> saveMerchantComment(Integer fMid, Integer fUid, Integer fId, Integer fPid, JSONObject params);

    ResApi<Object> listMerchantCommentByMidAndState(Integer fMid, Integer fState);

    ResApi<Object> listMerchantCommentByPidAndState(Integer fPid, Integer fState);
}
