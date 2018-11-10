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
 * Build File @date: 2018/9/30 7:48
 * @Description TODO
 * @version 1.0
 */
package com.smxy.recipe.utils.api;

import com.baidu.aip.speech.AipSpeech;
import com.baidu.aip.speech.TtsResponse;
import com.smxy.recipe.utils.FastDfsClient;
import com.smxy.recipe.utils.ToolsApi;
import org.json.JSONObject;

import java.util.HashMap;

public class BaiduTtsApi {
    public static final String APP_ID = "14322043";
    public static final String API_KEY = "KqPSEuc71U2NaNZ8gFMYcwHy";
    public static final String SECRET_KEY = "BfLW3sC5uFOrEgsEQblV8nMkgGsfeQYN";

    /**
     * spd	String	语速，取值0-9，默认为5中语速
     * pit	String	音调，取值0-9，默认为5中语调
     * vol	String	音量，取值0-15，默认为5中音量
     * per	String	发音人选择, 0为女声，1为男声，
     * 3为情感合成-度逍遥，4为情感合成-度丫丫，默认为普通女
     */
    public static String sendVoiceData(String content){
        AipSpeech client = new AipSpeech(APP_ID, API_KEY, SECRET_KEY);
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);
        HashMap<String, Object> options = new HashMap<>(8);
        options.put("spd", "4");
        options.put("pit", "5");
        options.put("per", "4");
        TtsResponse res = client.synthesis(content, "zh", 1, options);
        byte[] data = res.getData();
        JSONObject res1 = res.getResult();
        String result = null;
        if (data != null) {
            try {
                result = ToolsApi.binaryFileUploadFile(data, "mp3");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (res1 != null) {
            System.out.println(res1.toString(2));
        }
        return result;
    }
}
