package com.smxy.recipe.service.socket;

import com.smxy.recipe.entity.MerchantChat;
import com.smxy.recipe.service.MerchantUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Demo MerchantUserSocketService
 *
 * @author Yangyihui
 * @date 2018/12/17 0017 12:53
 */
@Transactional(rollbackFor = Exception.class)
@Service("merchantUserSocketService")
public class MerchantUserSocketService {

    private final SimpMessagingTemplate template;
    private final MerchantUserService merchantUserService;

    @Autowired
    public MerchantUserSocketService(SimpMessagingTemplate template, MerchantUserService merchantUserService) {
        this.template = template;
        this.merchantUserService = merchantUserService;
    }

    public void changeChatState(MerchantChat merchantChat) {
        merchantUserService.changeChatRead(merchantChat);
    }
}
