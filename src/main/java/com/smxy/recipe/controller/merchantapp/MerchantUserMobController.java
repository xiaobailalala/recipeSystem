package com.smxy.recipe.controller.merchantapp;

import com.smxy.recipe.config.template.PathRestController;
import com.smxy.recipe.entity.MerchantUser;
import com.smxy.recipe.service.MerchantUserService;
import com.smxy.recipe.utils.ResApi;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.SessionKey;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.apache.shiro.web.session.mgt.WebSessionKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Demo MerchantUserMobController
 *
 * @author Yangyihui
 * @date 2018/11/29 0029 19:25
 */
@PathRestController("/merchantMob/merchantUserMob")
public class MerchantUserMobController {
    @SuppressWarnings("all")
    @Autowired
    private MerchantUserService merchantUserService;

    @PostMapping("/mobUserLogin")
    public ResApi<Object> mobUserLogin(MerchantUser merchantUser, HttpServletRequest request){
        return merchantUserService.mobUserLogin(merchantUser, request);
    }

    @PostMapping("/getUserBySessionId")
    public ResApi<Object> getUserBySessionId(String sessionID, HttpServletRequest request, HttpServletResponse response){
        SessionKey sessionKey = new WebSessionKey(sessionID, request, response);
        Session session = SecurityUtils.getSecurityManager().getSession(sessionKey);
        Object attribute = session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
        SimplePrincipalCollection collection = (SimplePrincipalCollection) attribute;
        MerchantUser merchantUser = (MerchantUser) collection.getPrimaryPrincipal();
        if (merchantUser != null) {
            MerchantUser user = (MerchantUser) session.getAttribute("merUser");
            if (user == null) {
                session.setAttribute("merUser", merchantUser);
            }
            return new ResApi<>(200, "success", merchantUser);
        } else {
            return null;
        }
    }

}
