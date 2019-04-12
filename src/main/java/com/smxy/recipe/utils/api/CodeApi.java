/**   
 * Copyright © 2018 eSunny Info. Developer Stu. All rights reserved.
 * 
 * @Package: com.smxy.recipe.utils 
 * @author: 雏实。   
 * Build File @date: 2018年7月4日 下午1:35:57 
 */
package com.smxy.recipe.utils.api;

/**
 * @author zpx
 *
 */

import net.sf.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class CodeApi {
	public static final String DEF_CHATSET = "UTF-8";
    public static final int DEF_CONN_TIMEOUT = 30000;
    public static final int DEF_READ_TIMEOUT = 30000;
    public static String userAgent =  "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.66 Safari/537.36";
    public static final int REG_VERIFY = 0;
    public static final int RESET_PWD = 1;

    public static final String APPKEY ="0b93822981d6d671aab5d9cea3605d73";

    public static void getRequest1(){
        String result =null;
        String url ="http://v.juhe.cn/sms/black";
        Map params = new HashMap(8);
        params.put("word","");
        params.put("key",APPKEY);
        try {
            String errorCode = "error_code";
            result =net(url, params, "GET");
            JSONObject object = JSONObject.fromObject(result);
            if(object.getInt(errorCode)==0){
                System.out.println(object.get("result"));
            }else{
                System.out.println(object.get("error_code")+":"+object.get("reason"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void getRequest(int type, String phoneNum,String code){
        String result =null;
        String url ="http://v.juhe.cn/sms/send";
        Map params = new HashMap(8);
        params.put("mobile",phoneNum);
        switch (type) {
            case REG_VERIFY:
                params.put("tpl_id","81664");
                params.put("tpl_value","%23code%23%3d"+code);
                break;
            case RESET_PWD:
                params.put("tpl_id","108004");
                break;
            default:
        }
        params.put("key",APPKEY);
        params.put("dtype","json");
        String errorCode = "error_code";
        try {
            result = net(url, params, "GET");
            JSONObject object = JSONObject.fromObject(result);
            if(object.getInt(errorCode)==0){
                System.out.println(object.get("result"));
            }else{
                System.out.println(object.get("error_code")+":"+object.get("reason"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
 
 
 
    public static void main(String[] args) {
 
    }
 
    /**
     *
     * @param strUrl 请求地址
     * @param params 请求参数
     * @param method 请求方法
     * @return  网络请求字符串
     * @throws Exception
     */
    public static String net(String strUrl, Map params,String method) throws Exception {
        HttpURLConnection conn = null;
        BufferedReader reader = null;
        String rs = null;
        try {
            String currentMethod = "GET";
            StringBuffer sb = new StringBuffer();
            if(method==null || method.equals(currentMethod)){
                strUrl = strUrl+"?"+urlencode(params);
            }
            URL url = new URL(strUrl);
            conn = (HttpURLConnection) url.openConnection();
            if(method==null || method.equals(currentMethod)){
                conn.setRequestMethod(currentMethod);
            }else{
                conn.setRequestMethod("POST");
                conn.setDoOutput(true);
            }
            conn.setRequestProperty("User-agent", userAgent);
            conn.setUseCaches(false);
            conn.setConnectTimeout(DEF_CONN_TIMEOUT);
            conn.setReadTimeout(DEF_READ_TIMEOUT);
            conn.setInstanceFollowRedirects(false);
            conn.connect();
            String currentMethodPost = "POST";
            if (params!= null && method.equals(currentMethodPost)) {
                try {
                    DataOutputStream out = new DataOutputStream(conn.getOutputStream());
                        out.writeBytes(urlencode(params));
                } catch (Exception e) {
                    // TODO: handle exception
                }
            }
            InputStream is = conn.getInputStream();
            reader = new BufferedReader(new InputStreamReader(is, DEF_CHATSET));
            String strRead = null;
            while ((strRead = reader.readLine()) != null) {
                sb.append(strRead);
            }
            rs = sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                reader.close();
            }
            if (conn != null) {
                conn.disconnect();
            }
        }
        return rs;
    }

    /**
     * 将map型转为请求参数型
     */
    public static String urlencode(Map<String,Object>data) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry i : data.entrySet()) {
            try {
                sb.append(i.getKey()).append("=").append(URLEncoder.encode(i.getValue()+"","UTF-8")).append("&");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
}
