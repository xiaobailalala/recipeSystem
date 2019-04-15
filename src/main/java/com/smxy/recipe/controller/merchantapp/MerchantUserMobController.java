package com.smxy.recipe.controller.merchantapp;

import com.alibaba.fastjson.JSONObject;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

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
    public ResApi<Object> mobUserLogin(MerchantUser merchantUser, HttpServletRequest request) {
        return merchantUserService.mobUserLogin(merchantUser, request);
    }

    @PostMapping("/getUserBySessionId")
    public ResApi<Object> getUserBySessionId(String sessionID, HttpServletRequest request, HttpServletResponse response) {
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

    @PostMapping("/forgetPassword")
    public ResApi<String> forgetPassword(@RequestParam("password") String password, @RequestParam("account") String account) {
        return merchantUserService.forgetPassword(account, password);
    }

    @PostMapping("/editorUserPassword/{id}")
    public ResApi<String> editorUserPassword(@RequestParam("newPassword") String fPassword, @RequestParam("oldPassword") String oldPassword, @PathVariable("id") Integer fId) {
        return merchantUserService.editorUserPassword(fPassword, oldPassword, fId);
    }

    @PostMapping("/editorUserInfo/{id}/{type}")
    public ResApi<String> editorUserInfo(@PathVariable("id") Integer userId, @PathVariable("type") String type, JSONObject params) {
        switch (type) {
            case "name":
                return editorShopName(userId, params);
            case "sign":
                return editorShopSign(userId, params);
            case "address":
                return editorShopAddress(userId, params);
            case "birth":
                return editorUserBirthday(userId, params);
            default:
                return ResApi.getError(500, "type有误");
        }
    }

    private ResApi<String> editorShopName(Integer userId, JSONObject params) {
        String shopName = params.getString("shopName");
        return merchantUserService.editorShopName(userId, shopName);
    }

    private ResApi<String> editorShopSign(Integer userId, JSONObject params) {
        String shopSign = params.getString("shopSign");
        return merchantUserService.editorShopSign(userId, shopSign);
    }

    private ResApi<String> editorShopAddress(Integer userId, JSONObject params) {
        String shopAddress = params.getString("shopAddress");
        return merchantUserService.editorShopAddress(userId, shopAddress);
    }

    private ResApi<String> editorUserBirthday(Integer userId, JSONObject params) {
        String shopAddress = params.getString("shopAddress");
        return merchantUserService.editorUserBirthday(userId, shopAddress);
    }

    @PostMapping("/editorImage/{id}")
    public ResApi<String> editorImage(MultipartFile editorImage, @PathVariable("id") Integer fId, HttpServletRequest request) {
        System.out.println(editorImage.getOriginalFilename());
        return merchantUserService.editorUserCoverById(editorImage, fId, request);
    }


}
