package com.smxy.recipe.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.smxy.recipe.dao.*;
import com.smxy.recipe.entity.*;
import com.smxy.recipe.realm.LoginType;
import com.smxy.recipe.realm.UserToken;
import com.smxy.recipe.service.MerchantUserService;
import com.smxy.recipe.utils.ResApi;
import com.smxy.recipe.utils.ToolsApi;
import com.sun.deploy.uitoolkit.ToolkitStore;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Demo MerchantUserServiceImpl
 *
 * @author Yangyihui
 * @date 2018/11/12 0012 21:36
 */
@Transactional(rollbackFor = Exception.class)
@Service("merchantUserService")
public class MerchantUserServiceImpl implements MerchantUserService {

    private final MerchantUserDao merchantUserDao;
    private final AdminUserRoleDao adminUserRoleDao;
    private final AdminRoleDao adminRoleDao;
    private final MerchantUserRoleDao merchantUserRoleDao;
    private final SimpMessagingTemplate simpMessagingTemplate;
    private final MerchantChatDao merchantChatDao;
    private final CommonUserDao commonUserDao;
    private final MerchantUserCommonUserDao merchantUserCommonUserDao;
    private final MerchantProductDao merchantProductDao;
    private final MerchantOrderDao merchantOrderDao;
    private final MerchantViewsDao merchantViewsDao;
    private final MerchantUserLinkmanDao merchantUserLinkmanDao;

    private static final String MERCHANT_LOGIN_TYPE = LoginType.MERCHANT.toString();


    @Autowired
    public MerchantUserServiceImpl(MerchantUserLinkmanDao merchantUserLinkmanDao, MerchantUserDao merchantUserDao, AdminUserRoleDao adminUserRoleDao, AdminRoleDao adminRoleDao, MerchantUserRoleDao merchantUserRoleDao, SimpMessagingTemplate simpMessagingTemplate, MerchantChatDao merchantChatDao, CommonUserDao commonUserDao, MerchantUserCommonUserDao merchantUserCommonUserDao, MerchantProductDao merchantProductDao, MerchantOrderDao merchantOrderDao, MerchantViewsDao merchantViewsDao) {
        this.merchantUserDao = merchantUserDao;
        this.adminUserRoleDao = adminUserRoleDao;
        this.adminRoleDao = adminRoleDao;
        this.merchantUserRoleDao = merchantUserRoleDao;
        this.simpMessagingTemplate = simpMessagingTemplate;
        this.merchantChatDao = merchantChatDao;
        this.commonUserDao = commonUserDao;
        this.merchantUserLinkmanDao = merchantUserLinkmanDao;
        this.merchantUserCommonUserDao = merchantUserCommonUserDao;
        this.merchantProductDao = merchantProductDao;
        this.merchantOrderDao = merchantOrderDao;
        this.merchantViewsDao = merchantViewsDao;
    }

    private static final Logger logger = LoggerFactory.getLogger(MerchantUserServiceImpl.class);

    @Override
    public ResApi<String> userLogin(MerchantUser merchantUser, HttpServletRequest request) {
        ResApi<String> resApi = new ResApi<>(500, "系统出错", "error");
        Subject currentUser = SecurityUtils.getSubject();
        try {
            if (!currentUser.isAuthenticated()) {
                UserToken token = new UserToken(merchantUser.getFAccount(), merchantUser.getFPassword(), MERCHANT_LOGIN_TYPE);
                token.setRememberMe(false);
                try {
                    currentUser.login(token);
                    merchantUser = (MerchantUser) currentUser.getPrincipal();
                    SecurityUtils.getSubject().getSession().setAttribute("merUser", merchantUser);
                } catch (UnknownAccountException ae) {
                    resApi = new ResApi<>(501, "该账号不存在。", "failed");
                    return resApi;
                } catch (IncorrectCredentialsException ice) {
                    resApi = new ResApi<>(502, "您输入的密码不正确。", "failed");
                    return resApi;
                }
            }
            return new ResApi<>(200, "success", "success");
        } catch (Exception e) {
            return resApi;
        }

    }

    @Override
    public ResApi<String> editorUserCoverById(MultipartFile editorImage, Integer fId, HttpServletRequest request) {
        MerchantUser oldMerchantUser = merchantUserDao.getMerchantUserById(fId);
        Map<String, Object> map = new HashMap<>(8);
        Subject subject = SecurityUtils.getSubject();
        if (editorImage.getSize() > 0) {
            ToolsApi.multipartFileDeleteFile(oldMerchantUser.getFCover());
            String filename = ToolsApi.multipartFileUploadFile(editorImage, null);
            map.put("fCover", filename);
            map.put("fId", fId);
            int result = merchantUserDao.updateUserCoverById(map);
            if (result > 0) {
                subject.getSession().setAttribute("merUser", merchantUserDao.getMerchantUserById(fId));
                MerchantUser newUser = (MerchantUser) subject.getPrincipal();
                newUser.setFCover(filename);
                return ResApi.getSuccess();
            } else {
                return ResApi.getError();
            }
        } else {
            return ResApi.getError();
        }
    }

    @Override
    public ResApi<Object> mobUserLogin(MerchantUser merchantUser, HttpServletRequest request) {
        ResApi<Object> resApi = new ResApi<>(500, "系统出错", "error");
        Subject currentUser = SecurityUtils.getSubject();
        Map<String, Object> map = new HashMap<>(8);
        try {
            if (!currentUser.isAuthenticated()) {
                UserToken token = new UserToken(merchantUser.getFAccount(), merchantUser.getFPassword(), MERCHANT_LOGIN_TYPE);
                token.setRememberMe(false);
                try {
                    currentUser.login(token);
                    merchantUser = (MerchantUser) currentUser.getPrincipal();
                    SecurityUtils.getSubject().getSession().setAttribute("merUser", merchantUser);
                    map.put("token", currentUser.getSession().getId());
                    System.out.println(currentUser.getSession().getId());
                } catch (UnknownAccountException ae) {
                    resApi = new ResApi<>(501, "该账号不存在。", "failed");
                    return resApi;
                } catch (IncorrectCredentialsException ice) {
                    resApi = new ResApi<>(502, "您输入的密码不正确。", "failed");
                    return resApi;
                }
            }
            return new ResApi<>(200, "success", map);
        } catch (Exception e) {
            return resApi;
        }
    }

    @Override
    public ResApi<String> userRegister(MerchantUser merchantUser, HttpServletRequest request) {
        ResApi<String> resApi = new ResApi<>(500, "系统出错", "error");
        if (merchantUserDao.isAccount(merchantUser.getFAccount()) > 0) {
            return new ResApi<>(501, "手机号已存在", "phoneError");
        } else {
            int coverNum = (int) (Math.random() * 10 + 1);
            merchantUser.setFShopname(ToolsApi.stripXss(merchantUser.getFShopname()));
            merchantUser.setFPassword(ToolsApi.entryptBySaltMd5(merchantUser.getFPassword(), merchantUser.getFAccount()));
            merchantUser.setFCover("/src/images/merchant_head/" + coverNum + ".jpg");
            if (merchantUserDao.saveUserInfo(merchantUser) > 0) {
                resApi = new ResApi<>(200, "注册成功", "success");
//                request.getSession().setAttribute("merUser", merchantUser);
                Map<String, Integer> map = new HashMap<>(16);
                map.put("fMid", merchantUser.getFId());
                AdminRole role = new AdminRole();
                role.setFRolename("merchant");
                AdminRole adminRole = adminRoleDao.getAdminRoleByName(role);
                map.put("fRid", adminRole.getFId());
                adminUserRoleDao.saveMerchantInfo(map);
            }
            return resApi;
        }
    }

    @Cacheable(value = "MerchantVerifyRole", key = "#mid")
    @Override
    public List<AdminRole> verifyRole(Integer mid) {
        List<AdminRole> adminRoles = new ArrayList<>();
        for (AdminUserRole adminUserRole : merchantUserRoleDao.getMerchantUserRoleByFmid(mid)) {
            adminRoles.add(adminUserRole.getAdminRole());
        }
        return adminRoles;
    }

    @Cacheable(value = "MerchantVerifyPermission", key = "#mid")
    @Override
    public List<AdminPermission> verifyPermission(Integer mid) {
        List<AdminPermission> adminPermissions = new ArrayList<>();
        for (AdminUserRole adminUserRole : merchantUserRoleDao.getMerchantUserRoleByFmid(mid)) {
            for (AdminRolePermission adminRolePermission : adminUserRole.getAdminRole().getAdminRolePermissions()) {
                System.out.println(adminRolePermission.getAdminPermission());
                adminPermissions.add(adminRolePermission.getAdminPermission());
            }
        }
        return adminPermissions;
    }

    @Override
    public ResApi<String> editorUserDetails(MerchantUser merchantUser, Integer fId) {
        MerchantUser oldMerchantUser = merchantUserDao.getMerchantUserById(fId);
        merchantUser.setFId(fId);
        if ("".equals(merchantUser.getFProvince())) {
            merchantUser.setFProvince(oldMerchantUser.getFProvince());
        }
        if ("".equals(merchantUser.getFCity())) {
            merchantUser.setFCity(oldMerchantUser.getFCity());
        }
        if ("".equals(merchantUser.getFArea())) {
            merchantUser.setFArea(oldMerchantUser.getFArea());
        }
        if ("".equals(merchantUser.getFStreet())) {
            merchantUser.setFStreet(oldMerchantUser.getFStreet());
        }
        if ("".equals(merchantUser.getFSignature())) {
            merchantUser.setFSignature(oldMerchantUser.getFSignature());
        }
        Subject subject = SecurityUtils.getSubject();
        merchantUser.setFShopname(ToolsApi.stripXss(merchantUser.getFShopname()));
        Integer result = merchantUserDao.updateMerchantUserInfo(merchantUser);
        if (result > 0) {
            subject.getSession().setAttribute("merUser", merchantUserDao.getMerchantUserById(fId));
            return ResApi.getSuccess();
        } else {
            return ResApi.getError();
        }
    }

    @Override
    public ResApi<String> editorUserPassword(String fPassword, String oldPassword, Integer fId) {
        Map<String, Object> map = new HashMap<>(8);
        MerchantUser merchantUser = merchantUserDao.getMerchantUserById(fId);
        String oldPass = ToolsApi.entryptBySaltMd5(oldPassword, merchantUser.getFAccount());
        if (oldPass.equals(merchantUser.getFPassword())) {
            String password = ToolsApi.entryptBySaltMd5(fPassword, merchantUser.getFAccount());
            map.put("fPassword", password);
            map.put("fId", fId);
            Integer result = merchantUserDao.updateMerchantUserPasswordById(map);
            if (result > 0) {
                return ResApi.getSuccess();
            } else {
                return ResApi.getError();
            }
        } else {
            return new ResApi<>(408, "原密码错误", "passwordError");
        }
    }

    @Override
    public ResApi<Object> getIndexData(Integer userId) {
        JSONObject result = new JSONObject();
        if (userId == null) {
            return new ResApi<>(500, "error", null);
        }
        List<MerchantOrder> finishedOrderList = merchantOrderDao.getOrderByTypeAndIdGroupByuserId(userId, 1);
        MerchantUser merchantUser = merchantUserDao.getMerchantUserById(userId);
        List<MerchantOrder> merchantOrderList = merchantOrderDao.getOrderByUserId(userId);

        result.put("userCount", finishedOrderList.size());
        result.put("productCount", merchantUser.getMerchantUserProducts().size());
        result.put("orderCount", merchantOrderList.size());
        result.put("revenue", merchantUser.getFRevenue());
        return new ResApi<>(200, "success", result);
    }

    @Override
    public ResApi<Object> toMerchantSwitch(MultipartFile file, MerchantChat merchantChat) {
        if (merchantChat.getFType() == 0) {
            merchantChat.setFContent(ToolsApi.base64Encode(merchantChat.getFContent()));
        } else {
            merchantChat.setFUrl(ToolsApi.multipartFileUploadFile(file, null));
        }
        String dateTime = System.currentTimeMillis()+"";
        merchantChatDao.insertMessage(merchantChat.setFDatetime(dateTime));
        String[] timeArr = merchantChat.getFRelease().split(" ");
        MerchantUserLinkman merchantUserLinkman = new MerchantUserLinkman().setFUid(merchantChat.getFUid()).setFOid(merchantChat.getFOid()).
                setFUser(merchantChat.getFUser()).setFLastMsg(merchantChat.getFType() == 0 ? merchantChat.getFContent() : ToolsApi.base64Encode("[图片]")).
                setFLastDatetime(dateTime).setFLastDate(timeArr[0]);
        synchronized (this) {
            if (merchantUserLinkmanDao.queryJudgeExist(merchantChat.getFUid(), merchantChat.getFOid(), merchantChat.getFUser()) != null) {
                merchantUserLinkmanDao.updateInfo(merchantUserLinkman);
            } else {
                merchantUserLinkmanDao.insertInfo(merchantUserLinkman);
            }
            if (merchantUserLinkmanDao.queryJudgeExist(merchantChat.getFOid(), merchantChat.getFUid(), merchantChat.getFUser() == 0 ? 1 : 0) != null) {
                merchantUserLinkmanDao.updateInfo(merchantUserLinkman.setFUid(merchantChat.getFOid()).setFOid(merchantChat.getFUid()).setFUser(merchantChat.getFUser() == 0 ? 1 : 0));
            } else {
                merchantUserLinkmanDao.insertInfo(merchantUserLinkman.setFUid(merchantChat.getFOid()).setFOid(merchantChat.getFUid()).setFUser(merchantChat.getFUser() == 0 ? 1 : 0));
            }
        }
        simpMessagingTemplate.convertAndSend("/merchantChat/" + (merchantChat.getFUser() == 1 ? "user" : "merchant") + "/" + merchantChat.getFOid(),
                merchantChat.setFContent(ToolsApi.base64Decode(merchantChat.getFContent())));
        return ResApi.getSuccess(merchantChat);
    }

    @Override
    public ResApi<Object> getMerchantChatMessage(MerchantChat merchantChat) {
        Map<String, Object> data = new HashMap<>(8);
        List<MerchantChat> merchantChats = new LinkedList<>(merchantChatDao.queryMessage(new MerchantChat().
                setFUser(merchantChat.getFUser()).setFUid(merchantChat.getFUid()).
                setFOid(merchantChat.getFOid())));
        merchantChats.addAll(merchantChatDao.queryMessage(new MerchantChat().
                setFUser(merchantChat.getFUser() == 0 ? 1 : 0).setFUid(merchantChat.getFOid()).
                setFOid(merchantChat.getFUid())));
        merchantChats.sort(Comparator.comparing(MerchantChat::getFDatetime));
        merchantChats.forEach(item -> {
            if (item.getFType() == 0) {
                item.setFContent(ToolsApi.base64Decode(item.getFContent()));
            }
        });
        this.changeChatState(merchantChat);
        data.put("list", merchantChats);
        data.put("otherInfo", merchantChat.getFUser() == 0 ? merchantUserDao.getMerchantUserByIdBrief(merchantChat.getFOid()) :
                commonUserDao.getInfoByIdBrief(merchantChat.getFOid()));
        return ResApi.getSuccess(data);
    }

    @Override
    public ResApi<String> changeChatRead(MerchantChat merchantChat) {
        this.changeChatState(merchantChat);
        return ResApi.getSuccess();
    }

    @Override
    public ResApi<Object> linkmanList(MerchantUserLinkman merchantUserLinkman) {
        List<MerchantUserLinkman> merchantUserLinkmen = merchantUserLinkmanDao.queryInfo(merchantUserLinkman);
        for (MerchantUserLinkman item : merchantUserLinkmen) {
            item.setFLastMsg(ToolsApi.base64Decode(item.getFLastMsg()));
            if (item.getFUser() == 0) {
                item.setMerchantUser(merchantUserDao.getMerchantUserByIdBrief(item.getFOid()));
            } else {
                item.setCommonUser(commonUserDao.getInfoByIdBrief(item.getFOid()));
            }
            item.setFUnread(merchantChatDao.queryUnreadCount(item.getFUid(), item.getFOid(), item.getFUser() == 0 ? 1 : 0));
        }
        return ResApi.getSuccess(merchantUserLinkmen);
    }

    private void changeChatState(MerchantChat merchantChat) {
        merchantChatDao.updateMessageState(new MerchantChat().
                setFState(1).setFUser(merchantChat.getFUser() == 0 ? 1 : 0).setFUid(merchantChat.getFOid()).
                setFOid(merchantChat.getFUid()));
    }

    @Override
    public ResApi<String> forgetPassword(String account, String password) {
        MerchantUser userByAccount = merchantUserDao.getMerchantUserByAccount(account);
        Map<String, Object> map = new HashMap<>(8);
        if (userByAccount == null) {
            return ResApi.getError(500, "没有此用户,请确认手机号无误");
        }
        String newPassword = ToolsApi.entryptBySaltMd5(password, userByAccount.getFAccount());
        map.put("fPassword", newPassword);
        map.put("fId", userByAccount.getFId());
        Integer result = merchantUserDao.updateMerchantUserPasswordById(map);
        if (result > 0) {
            return ResApi.getSuccess();
        } else {
            return ResApi.getError();
        }
    }

    @Override
    public ResApi<String> editorShopName(Integer userId, String shopName) {
        MerchantUser merchantUserById = merchantUserDao.getMerchantUserById(userId);
        if (merchantUserById == null) {
            logger.error("没有查到用户信息！");
            return ResApi.getError();
        }
        merchantUserById.setFShopname(shopName);
        Subject subject = SecurityUtils.getSubject();
        Integer result = merchantUserDao.updateMerchantUserInfo(merchantUserById);
        if (result > 0) {
            MerchantUser newUser = (MerchantUser) subject.getPrincipal();
            newUser.setFShopname(shopName);
            return ResApi.getSuccess();
        } else {
            return ResApi.getError();
        }
    }

    @Override
    public ResApi<String> editorShopSign(Integer userId, String shopSign) {
        MerchantUser merchantUserById = merchantUserDao.getMerchantUserById(userId);
        if (merchantUserById == null) {
            logger.error("没有查到用户信息！");
            return ResApi.getError();
        }
        merchantUserById.setFSignature(shopSign);
        Integer result = merchantUserDao.updateMerchantUserInfo(merchantUserById);
        Subject subject = SecurityUtils.getSubject();
        if (result > 0) {
            MerchantUser newUser = (MerchantUser) subject.getPrincipal();
            newUser.setFSignature(shopSign);
            return ResApi.getSuccess();
        } else {
            return ResApi.getError();
        }
    }

    @Override
    public ResApi<String> editorShopAddress(Integer userId, String shopAddress) {
        String[] address = shopAddress.split("-", -1);
        MerchantUser merchantUserById = merchantUserDao.getMerchantUserById(userId);
        if (merchantUserById == null) {
            logger.error("没有查到用户信息！");
            return ResApi.getError();
        }
        merchantUserById.setFProvince("".equals(address[0]) ? "" : address[0]);
        merchantUserById.setFCity("".equals(address[1]) ? "" : address[1]);
        merchantUserById.setFArea("".equals(address[2]) ? "" : address[2]);
        merchantUserById.setFStreet("".equals(address[3]) ? "" : address[3]);
        Integer result = merchantUserDao.updateMerchantUserInfo(merchantUserById);
        Subject subject = SecurityUtils.getSubject();
        if (result > 0) {
            MerchantUser newUser = (MerchantUser) subject.getPrincipal();
            newUser.setFProvince("".equals(address[0]) ? "" : address[0]);
            newUser.setFCity("".equals(address[1]) ? "" : address[1]);
            newUser.setFArea("".equals(address[2]) ? "" : address[2]);
            newUser.setFStreet("".equals(address[3]) ? "" : address[3]);
            return ResApi.getSuccess();
        } else {
            return ResApi.getError();
        }
    }

    @Override
    public ResApi<String> editorUserBirthday(Integer userId, String birthday) {
        MerchantUser merchantUserById = merchantUserDao.getMerchantUserById(userId);
        if (merchantUserById == null) {
            logger.error("没有查到用户信息！");
            return ResApi.getError();
        }
        merchantUserById.setFBirth(birthday);
        Integer result = merchantUserDao.updateMerchantUserInfo(merchantUserById);
        Subject subject = SecurityUtils.getSubject();
        if (result > 0) {
            MerchantUser newUser = (MerchantUser) subject.getPrincipal();
            newUser.setFBirth(birthday);
            return ResApi.getSuccess();
        } else {
            return ResApi.getError();
        }
    }

    @Override
    public ResApi<Object> getViewsCount(Integer fMid) {
        Integer viewCount = merchantViewsDao.getViewsCountByMerchantId(fMid);
        return ResApi.getSuccess(viewCount);
    }

    @Override
    public ResApi<JSONObject> getViewsCountByWeek(Integer fMid) {
        JSONObject result = new JSONObject();
        List<MerchantViews> merchantViewsList = merchantViewsDao.getViewWeekByMerchantId(fMid, ToolsApi.getPast7DayTime(7), ToolsApi.getCurrentYYYYMMDDTime());
        List<String> weekDays = new ArrayList<>();
        List<Integer> weekCounts = new ArrayList<>();
        for (MerchantViews merchantViews : merchantViewsList) {
            weekDays.add(merchantViews.getFTime());
            weekCounts.add(merchantViews.getFCount());
        }
        result.put("time", weekDays);
        result.put("count", weekCounts);
        return new ResApi<>(200, "success", result);
    }

    @Override
    public ResApi<String> editorUserAccount(Integer userId, JSONObject params) {
        String oldAccount = merchantUserDao.getMerchantUserById(userId).getFAccount();
        String oldAccountFromParams = params.getString("oldAccount");
        if (!oldAccount.equals(oldAccountFromParams)) {
            return ResApi.getError();
        }
        String newAccount = params.getString("newAccount");
        Integer result = merchantUserDao.updateUserAccountById(userId, newAccount);
        if (result > 0) {
            return ResApi.getSuccess();
        } else {
            return ResApi.getError();
        }
    }

    @Override
    public void saveMerchantViewsCount() {
        List<Integer> merchantIdList = merchantUserDao.getAllMerchantId();
        List<Integer> merchantViewIdList = merchantViewsDao.getAllViewsMerchantId();
        List<Integer> merIdList = new ArrayList<>();
        List<MerchantViews> viewsList = new ArrayList<>();
        for (Integer merid : merchantIdList) {
            if (!merchantViewIdList.contains(merid)) {
                //添加所有在访问量表中未包含的id
                merIdList.add(merid);
            }
        }
        //插入访问表中不存在的商家访问量信息
        synchronized (this) {
            List<MerchantUser> merchantUserList = merchantUserDao.getMerchantUserByIds(merIdList);
            for (MerchantUser user : merchantUserList) {
                viewsList.add(new MerchantViews(user.getFCount(), ToolsApi.getCurrentYYYYMMDDTime(), user.getFId()));
            }
            Integer result = merchantViewsDao.insertViewsList(viewsList);
        }

        //新增访问量表中已存在的商家访问量信息
        synchronized (this) {
            List<MerchantUser> existMerchantUserList = merchantUserDao.getMerchantUserByIds(merchantViewIdList);
            List<MerchantViews> yesterDayViews = merchantViewsDao.getViewsCountByMerchantIdsAndTime(merchantViewIdList, ToolsApi.getYesterdayYYYYMMDDTIme(), ToolsApi.getYesterdayYYYYMMDDTIme());
            List<MerchantViews> addViewsList = new ArrayList<>();
            Map<Integer, Integer> userIdMap = existMerchantUserList.stream().collect(Collectors.toMap(MerchantUser::getFId, MerchantUser::getFCount));
            Map<Integer, Integer> viewsIdMap = yesterDayViews.stream().collect(Collectors.toMap(MerchantViews::getFMid, MerchantViews::getFCount));
            for (Integer userid : viewsIdMap.keySet()) {
                int count = userIdMap.get(userid) - viewsIdMap.get(userid);
                addViewsList.add(new MerchantViews(count, ToolsApi.getCurrentYYYYMMDDTime(), userid));
            }
            Integer result = merchantViewsDao.insertViewsList(addViewsList);
        }
    }

    @Override
    public ResApi<Object> getMerchantUserCount(Integer fMid) {
        List<MerchantOrder> finishedOrderList = merchantOrderDao.getOrderByTypeAndIdGroupByuserId(fMid, 1);
        return new ResApi<>(200, "success", finishedOrderList.size());
    }

    @Override
    public ResApi<Object> getMerchantFansCount(Integer fMid) {
        return new ResApi<>(200, "success", merchantUserCommonUserDao.getUserCountById(fMid).size());
    }

    @Override
    public ResApi<Object> getMerchantFansUser(Integer fMid) {
        List<CommonUser> commonUserList = merchantUserCommonUserDao.getUserCountById(fMid);
        return new ResApi<>(200, "success", commonUserList);
    }

    @Override
    public ResApi<String> saveWithdrawMoney(Integer fMid, Double money) {
        MerchantUser merchantUser = merchantUserDao.getMerchantUserByIdBrief(fMid);
        if (merchantUser.getFRevenue() < money) {
            return new ResApi<>(505, "error", "没有这么多钱，小伙子别贪心啊！");
        }
        double withdraw = merchantUser.getFRevenue() - money;
        Integer result = merchantUserDao.updateUserWithdraw(fMid, money);
        Integer result1 = merchantUserDao.updateUserRevenue(fMid, withdraw);
        if (result > 0 && result1 > 0) {
            return ResApi.getSuccess();
        } else {
            return ResApi.getError();
        }

    }

    @Override
    public ResApi<Object> getUserWithdraw(Integer fMid) {
        return new ResApi<>(200, "success", merchantUserDao.getUserRevenue(fMid));
    }




}
