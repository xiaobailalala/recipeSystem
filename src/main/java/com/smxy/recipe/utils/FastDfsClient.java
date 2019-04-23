/**
 * Copyright © 2018 eSunny Info. Developer Stu. All rights reserved.
 *
 * @Package:
 * @author: zpx
 * Build File @date: 2018/8/27 21:38
 * @Description TODO
 * @version 1.0
 */
package com.smxy.recipe.utils;

import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FastDfsClient {
    private static final Logger logger = LoggerFactory.getLogger(FastDfsClient.class);
    private static TrackerClient trackerClient;
    private static TrackerServer trackerServer;
    private static StorageClient1 storageClient;
    private static StorageServer storageServer;

    static {
        try {
            String initFileName = "fastdfs_server.conf";
            myFastDfsInit(initFileName);
            trackerClient = new TrackerClient();
            trackerServer = trackerClient.getConnection();
            storageClient = new StorageClient1(trackerServer, storageServer);
        } catch (Exception e) {
            logger.error("FastDFS Client Init Fail!",e);
        }
    }

    /**
     * 原读取配置
     * String filePath = new ClassPathResource("fastdfs_server.conf").getFile().getAbsolutePath();
     * ClientGlobal.init(filePath);
     */

    public static String uploadFile(String fileName, String extName, NameValuePair[] pairs) throws Exception{
        return storageClient.upload_file1(fileName, extName, pairs);
    }

    static void deleteFile(String fileName) throws Exception {
        storageClient.delete_file1(fileName);
    }

    static String uploadBinaryFile(byte[] source, String extName, NameValuePair[] pairs) throws Exception{
        return storageClient.upload_file1(source, extName, pairs);
    }

    private static Map<String, Object> getInitParam(String initFileName) {
        Map<String, Object> initParam = new HashMap<>(8);
        try {
            InputStream inputStream = new ClassPathResource(initFileName).getInputStream();
            String charSet = "UTF-8";
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, charSet));
            String line;
            while ((line = br.readLine()) != null) {
                String initStr[] = line.split(" = ");
                if (initStr.length == 1) {
                    initParam.put(initStr[0], null);
                }
                if (initStr.length == 2) {
                    initParam.put(initStr[0], initStr[1]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return initParam;
    }

    private static void myFastDfsInit(String initFileName) throws IOException, MyException {
        Map<String, Object> initParam = getInitParam(initFileName);
        ClientGlobal.g_connect_timeout = getIntValue(initParam, "connect_timeout", 5);
        if (ClientGlobal.g_connect_timeout < 0) {
            ClientGlobal.g_connect_timeout = 5;
        }

        ClientGlobal.g_connect_timeout *= 1000;
        ClientGlobal.g_network_timeout = getIntValue(initParam, "network_timeout", 30);
        if (ClientGlobal.g_network_timeout < 0) {
            ClientGlobal.g_network_timeout = 30;
        }

        ClientGlobal.g_network_timeout *= 1000;
        ClientGlobal.g_charset = getStrValue(initParam, "charset");
        if (ClientGlobal.g_charset == null || ClientGlobal.g_charset.length() == 0) {
            ClientGlobal.g_charset = "ISO8859-1";
        }

        String[] szTrackerServers = getValues(initParam, "tracker_server");
        if (szTrackerServers == null) {
            throw new MyException("item \"tracker_server\" in MyfileSystem not found");
        } else {
            InetSocketAddress[] tracker_servers = new InetSocketAddress[szTrackerServers.length];

            for(int i = 0; i < szTrackerServers.length; ++i) {
                String[] parts = szTrackerServers[i].split("\\:", 2);
                if (parts.length != 2) {
                    throw new MyException("the value of item \"tracker_server\" is invalid, the correct format is host:port");
                }

                tracker_servers[i] = new InetSocketAddress(parts[0].trim(), Integer.parseInt(parts[1].trim()));
            }

            ClientGlobal.g_tracker_group = new TrackerGroup(tracker_servers);
            ClientGlobal.g_tracker_http_port = getIntValue(initParam, "http.tracker_http_port", 80);
            ClientGlobal.g_anti_steal_token = getBoolValue(initParam, "http.anti_steal_token", false);
            if (ClientGlobal.g_anti_steal_token) {
                ClientGlobal.g_secret_key = getStrValue(initParam, "http.secret_key");
            }

        }
    }

    private static int getIntValue(Map<String, Object> initParam, String paramName, int defaultValue) {
        String szValue = getStrValue(initParam, paramName);
        return szValue == null ? defaultValue : Integer.parseInt(szValue);
    }

    private static String getStrValue(Map<String, Object> initParam, String paramName) {
        Object obj = initParam.get(paramName);
        if (obj == null) {
            return null;
        } else {
            return obj instanceof String ? (String)obj : (String)((ArrayList)obj).get(0);
        }
    }

    private static String[] getValues(Map<String, Object> initParam, String paramName) {
        Object obj = initParam.get(paramName);
        if (obj == null) {
            return null;
        } else {
            String[] values;
            if (obj instanceof String) {
                values = new String[]{(String)obj};
                return values;
            } else {
                Object[] objs = ((ArrayList)obj).toArray();
                values = new String[objs.length];
                System.arraycopy(objs, 0, values, 0, objs.length);
                return values;
            }
        }
    }

    private static boolean getBoolValue(Map<String, Object> initParam, String paramName, boolean defaultValue) {
        String szValue = getStrValue(initParam, paramName);
        if (szValue == null) {
            return defaultValue;
        } else {
            String[] resStr = {"yes", "on", "true", "1"};
            return szValue.equalsIgnoreCase(resStr[0]) || szValue.equalsIgnoreCase(resStr[1]) || szValue.equalsIgnoreCase(resStr[2]) || szValue.equals(resStr[3]);
        }
    }


    public static void main(String[] args) {
        String str = "用户管理-user management-Người dùng quản lý.&&昵称-nickname-Biệt danh.&&用户-user-Người dùng&&用户账号-user account-Tài khoản người dùng&&用户密码-user password-Người dùng mật mã.&&分配角色-assign roles-Phân vai&&权限分配-permission assignment-Quyền phân phối&&角色-role-Nhân vật&&请选择-please choose-Hãy lựa chọn.&&新增角色-new role-Thêm nhân vật&&角色名称-role name-Tên nhân vật&&编辑-edit-Biên tập.&&删除-delete-Xoá&&信息-information-Thông tin&&确认删除此角色吗-are you sure you delete this role-Xác nhận xóa bỏ vai trò này không&&权限-jurisdiction-Quyền hạn&&角色管理-role management-Vai trò của quản lý.&&菜单管理-menu management-Quản lý thực đơn&&新增一级菜单-new first-level nenu-Thêm một thực đơn&&名称-name-Tên&&路径-route-Đường dẫn&&保存一级菜单-save first-level menu-Lưu một thực đơn&&必填项不能为空-necessary items should not be blank-hạng không rỗng&&登录-sign in-Đăng nhập&&操作成功-successful operation-Chiến dịch thành công.&&操作失败-failed operation-Chiến dịch thất bại.";
        String[] arrGroup = str.split("&&");
        StringBuilder result = new StringBuilder();
        for (String s : arrGroup) {
            String[] arrContent = s.split("-");
            result.append("\"").append(arrContent[0]).append("\": {\"cn\": \"").append(arrContent[0]).append("\", \"en\": \"").append(arrContent[1]).append("\", \"vn\": \"").append(arrContent[2]).append("\"},");
        }
        System.out.println(result.toString().substring(0, result.toString().length() - 1));

    }

}
