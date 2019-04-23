package com.smxy.recipe.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.smxy.recipe.dao.MerchantCommentDao;
import com.smxy.recipe.entity.MerchantComment;
import com.smxy.recipe.service.MerchantCommentService;
import com.smxy.recipe.utils.ResApi;
import com.smxy.recipe.utils.ToolsApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Demo MerchantCommentServiceImpl
 *
 * @author Yangyihui
 * @date 2019/4/22 0022 23:24
 */
@Transactional(rollbackFor = Exception.class)
@Service("merchantCommentService")
public class MerchantCommentServiceImpl implements MerchantCommentService {
    private final MerchantCommentDao merchantCommentDao;

    @Autowired
    public MerchantCommentServiceImpl(MerchantCommentDao merchantCommentDao) {
        this.merchantCommentDao = merchantCommentDao;
    }

    @Override
    public ResApi<String> saveMerchantComment(Integer fMid, Integer fUid, Integer fId, Integer fPid, JSONObject params) {
        String content = params.getString("content");
        Integer state = params.getInteger("state");
        Integer result = merchantCommentDao.insertComment(fMid, fPid, fUid, ToolsApi.getCurrentYYYYMMDDTime(), content, state, null, fId);
        if (result > 0) {
            return ResApi.getSuccess();
        } else {
            return ResApi.getError();
        }
    }

    @Override
    public ResApi<Object> listMerchantCommentByMidAndState(Integer fMid, Integer fState) {
        List<JSONObject> dataList = new ArrayList<>();
        JSONObject data;
        List<MerchantComment> commentList = merchantCommentDao.getMerchantCommentByMidAndState(fState, fMid);
        List<MerchantComment> answerCommentList = merchantCommentDao.getAnswerMerchantCommentByMidAndState(fState, fMid);
        Map<Integer, MerchantComment> commentMap = commentList.stream().collect(Collectors.toMap(MerchantComment::getFId, o -> o));
        Map<Integer, List<MerchantComment>> answerCommentMap = answerCommentList.stream().collect(Collectors.groupingBy(MerchantComment::getFReplyid));
        for (Integer commentId : commentMap.keySet()) {
            data = new JSONObject();
            data.put("comment", commentMap.get(commentId));
            data.put("answer", answerCommentMap.getOrDefault(commentId, null));
            dataList.add(data);
        }
        return ResApi.getSuccess(dataList);
    }

    @Override
    public ResApi<Object> listMerchantCommentByPidAndState(Integer fPid, Integer fState) {
        List<JSONObject> dataList = new ArrayList<>();
        JSONObject data;
        List<MerchantComment> commentList = merchantCommentDao.getMerchantCommentByPidAndState(fState, fPid);
        List<MerchantComment> answerCommentList = merchantCommentDao.getAnswerMerchantCommentByPidAndState(fState, fPid);
        Map<Integer, MerchantComment> commentMap = commentList.stream().collect(Collectors.toMap(MerchantComment::getFId, o -> o));
        Map<Integer, List<MerchantComment>> answerCommentMap = answerCommentList.stream().collect(Collectors.groupingBy(MerchantComment::getFReplyid));
        for (Integer commentId : commentMap.keySet()) {
            data = new JSONObject();
            data.put("comment", commentMap.get(commentId));
            data.put("answer", answerCommentMap.getOrDefault(commentId, null));
            dataList.add(data);
        }
        return ResApi.getSuccess(dataList);
    }
}
