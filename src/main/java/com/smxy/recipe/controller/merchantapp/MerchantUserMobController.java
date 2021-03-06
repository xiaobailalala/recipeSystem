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
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;
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

    private final MerchantUserService merchantUserService;

    @Autowired
    public MerchantUserMobController(MerchantUserService merchantUserService) {
        this.merchantUserService = merchantUserService;
    }

    @PostMapping("/mobUserLogin")
    public ResApi<Object> mobUserLogin(MerchantUser merchantUser, HttpServletRequest request) {
        return merchantUserService.mobUserLogin(merchantUser, request);
    }

    @PostMapping("/mobUserRegister")
    public ResApi<String> userRegister(MerchantUser merchantUser, HttpServletRequest request) {
        System.out.println(merchantUser.toString());
        return merchantUserService.userRegister(merchantUser, request);
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
    public ResApi<String> editorUserInfo(@PathVariable("id") Integer userId, @PathVariable("type") String type, @RequestBody JSONObject params) {
        JSONObject params1 = params.getJSONObject("params");
        switch (type) {
            case "name":
                return editorShopName(userId, params1);
            case "sign":
                return editorShopSign(userId, params1);
            case "address":
                return editorShopAddress(userId, params1);
            case "birth":
                return editorUserBirthday(userId, params1);
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

    @PostMapping("/editorUserAccount/{userId}")
    public ResApi<String> editorUserAccount(@PathVariable("userId") Integer userId, @RequestBody JSONObject params) {
        params = params.getJSONObject("params");
        return merchantUserService.editorUserAccount(userId, params);
    }

    @PostMapping("/editorImage/{id}")
    public ResApi<String> editorImage(MultipartFile editorImage, @PathVariable("id") Integer fId, HttpServletRequest request) {
        System.out.println(editorImage.getOriginalFilename());
        return merchantUserService.editorUserCoverById(editorImage, fId, request);
    }

    @PostMapping("/getViewsCount/{userId}")
    public ResApi<Object> getViewsCount(@PathVariable("userId") Integer fMid) {
        return merchantUserService.getViewsCount(fMid);
    }

    @PostMapping("/getViewsCountByWeek/{userId}")
    public ResApi<JSONObject> getViewsCountByWeek(@PathVariable("userId") Integer fMid) {
        return merchantUserService.getViewsCountByWeek(fMid);
    }


//    @Scheduled(cron = "0/3 * * * * *")
    public void saveMerchantViewsCount() {
        merchantUserService.saveMerchantViewsCount();
    }

    @GetMapping("/getMerchantUserCount/{fMid}")
    public ResApi<Object> getMerchantUserCount(@PathVariable("fMid") Integer fMid) {
        return merchantUserService.getMerchantUserCount(fMid);
    }

    @GetMapping("/getMerchantFansCount/{fMid}")
    public ResApi<Object> getMerchantFansCount(@PathVariable("fMid") Integer fMid) {
        return merchantUserService.getMerchantFansCount(fMid);
    }

    @GetMapping("/getMerchantFansUser/{fMid}")
    public ResApi<Object> getMerchantFansUser(@PathVariable("fMid") Integer fMid) {
        return merchantUserService.getMerchantFansUser(fMid);
    }

    //商家提现
    @PostMapping("/getWithdrawMoney/{fMid}")
    public ResApi<String> saveWithdrawMoney(@PathVariable("fMid") Integer fMid, @RequestParam("money") Double money) {
        return merchantUserService.saveWithdrawMoney(fMid, money);
    }

    @PostMapping("/getUserWithdraw/{fMid}")
    public ResApi<Object> getUserWithdraw(@PathVariable("fMid") Integer fMid) {
        return merchantUserService.getUserWithdraw(fMid);
    }
}
