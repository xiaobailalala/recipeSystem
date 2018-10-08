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
import com.smxy.recipe.utils.FastDFSClient;
import org.json.JSONObject;

import java.util.HashMap;

public class Baidu_TTSApi {
    //设置APPID/AK/SK
    public static final String APP_ID = "14322043";
    public static final String API_KEY = "KqPSEuc71U2NaNZ8gFMYcwHy";
    public static final String SECRET_KEY = "BfLW3sC5uFOrEgsEQblV8nMkgGsfeQYN";

    public static String sendVoiceData(String content){
        // 初始化一个AipSpeech
        AipSpeech client = new AipSpeech(APP_ID, API_KEY, SECRET_KEY);

        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);

        // 可选：设置代理服务器地址, http和socket二选一，或者均不设置
//        client.setHttpProxy("proxy_host", proxy_port);  // 设置http代理
//        client.setSocketProxy("proxy_host", proxy_port);  // 设置socket代理

        // 可选：设置log4j日志输出格式，若不设置，则使用默认配置
        // 也可以直接通过jvm启动参数设置此环境变量
//        System.setProperty("aip.log4j.conf", "path/to/your/log4j.properties");
        /**
         * spd	String	语速，取值0-9，默认为5中语速
         * pit	String	音调，取值0-9，默认为5中语调
         * vol	String	音量，取值0-15，默认为5中音量
         * per	String	发音人选择, 0为女声，1为男声，
         * 3为情感合成-度逍遥，4为情感合成-度丫丫，默认为普通女
         */
        HashMap<String, Object> options = new HashMap<>();
        options.put("spd", "4");
        options.put("pit", "5");
        options.put("per", "4");
        // 调用接口
        TtsResponse res = client.synthesis(content, "zh", 1, options);
        byte[] data = res.getData();
        JSONObject res1 = res.getResult();
        String result = null;
        if (data != null) {
            try {
                result = FastDFSClient.upload_binary_file(data, "mp3", null);
//              Util.writeBytesToFileSystem(data, "output.mp3");
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
