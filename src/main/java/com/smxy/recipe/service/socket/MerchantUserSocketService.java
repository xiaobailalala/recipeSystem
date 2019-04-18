package com.smxy.recipe.service.socket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;

/**
 * Demo MerchantUserSocketService
 *
 * @author Yangyihui
 * @date 2018/12/17 0017 12:53
 */
public class MerchantUserSocketService {

    private final SimpMessagingTemplate template;

    @Autowired
    public MerchantUserSocketService(SimpMessagingTemplate template) {
        this.template = template;
    }
}
