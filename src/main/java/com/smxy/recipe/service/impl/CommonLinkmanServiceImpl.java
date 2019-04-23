/**
 * Copyright © 2018 eSunny Info. Developer Stu. All rights reserved.
 * <p>
 * code is far away from bug with the animal protecting
 * <p>
 * ┏┓　　　┏┓
 * ┏┛┻━━━┛┻┓
 * ┃　　　　　　　┃
 * ┃　　　━　　　┃
 * ┃　┳┛　┗┳　┃
 * ┃　　　　　　　┃
 * ┃　　　┻　　　┃
 * ┃　　　　　　　┃
 * ┗━┓　　　┏━┛
 * 　　┃　　　┃神兽保佑
 * 　　┃　　　┃代码无BUG！
 * 　　┃　　　┗━━━┓
 * 　　┃　　　　　　　┣┓
 * 　　┃　　　　　　　┏┛
 * 　　┗┓┓┏━┳┓┏┛
 * 　　　┃┫┫　┃┫┫
 * 　　　┗┻┛　┗┻┛
 *
 * @Package:
 * @author: zpx
 * Build File @date: 2019/4/19 19:21
 * @Description TODO
 * @version 1.0
 */
package com.smxy.recipe.service.impl;

import com.smxy.recipe.dao.CommonChatDao;
import com.smxy.recipe.dao.CommonLinkmanDao;
import com.smxy.recipe.entity.CommonLinkman;
import com.smxy.recipe.service.CommonLinkmanService;
import com.smxy.recipe.utils.ResApi;
import com.smxy.recipe.utils.ToolsApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("commonLinkmanService")
@Transactional(rollbackFor = Exception.class)
public class CommonLinkmanServiceImpl implements CommonLinkmanService {

    private final CommonLinkmanDao commonLinkmanDao;
    private final CommonChatDao commonChatDao;

    @Autowired
    public CommonLinkmanServiceImpl(CommonLinkmanDao commonLinkmanDao, CommonChatDao commonChatDao) {
        this.commonLinkmanDao = commonLinkmanDao;
        this.commonChatDao = commonChatDao;
    }


    @Override
    public ResApi<Object> linkmanList(CommonLinkman commonLinkman) {
        List<CommonLinkman> commonLinkmen = commonLinkmanDao.queryInfo(commonLinkman);
        for (CommonLinkman item : commonLinkmen) {
            item.setFLastMsg(ToolsApi.base64Decode(item.getFLastMsg()));
            item.setFUnread(commonChatDao.queryUnreadCount(item.getFUid(), item.getFOid()));
        }
        return ResApi.getSuccess(commonLinkmen);
    }
}
