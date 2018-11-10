/**
 * Copyright © 2018 eSunny Info. Developer Stu. All rights reserved.
 *
 * @Package:
 * @author: zpx
 * Build File @date: 2018/9/2 16:47
 * @Description TODO
 * @version 1.0
 */
package com.smxy.recipe.utils;

import com.smxy.recipe.entity.Profession;
import com.smxy.recipe.service.ProfessionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

@Component
public class Test {

    private static final Logger logger = LoggerFactory.getLogger(Test.class);

    private static ProfessionService professionService;
    @Autowired
    public void setProfessionService(ProfessionService professionService) {
        logger.info("------> Autowired Successful");
        Test.professionService = professionService;
    }

    public static void execute(){
        new ThreadTest().start();
    }

    private static class ThreadTest extends Thread{
        @Override
        public void run() {
            List<Profession> professions = professionService.getAllInfo().getData();
            professions.forEach(item -> logger.info("------> " + item));
        }
    }

    //    static String[] getArr(String cmd){
//        InputStream fis = null;
//        Process process = null;
//        String[] arr=null;
//        try {
//            process = Runtime.getRuntime().exec(cmd);
//            fis = process.getInputStream();
//            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fis));
//            String s;
//            StringBuffer sb = new StringBuffer();
//            while((s=bufferedReader.readLine())!=null){
//                sb.append(s);
//            }
//            fis.close();
//            bufferedReader.close();
//            arr=sb.toString().split(" ");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return arr;
//    }
//    static void sendGet(String url){
//        String result="";
//        String line;
//        BufferedReader in = null;
//        URL realUrl = null;
//        try {
//            realUrl = new URL(url);
//            URLConnection conn = realUrl.openConnection();
//            conn.setRequestProperty("contentType","utf-8");
//            conn.setRequestProperty("content-type","application/x-www-form-urlencoded");
//            conn.setConnectTimeout(60);
//            conn.setReadTimeout(60);
//            conn.connect();
//            in = new BufferedReader(new InputStreamReader(
//                    conn.getInputStream(),"utf-8"));
//            StringBuffer ss = new StringBuffer();
//            while ((line = in.readLine()) != null) {
//                ss.append(line);
//            }
//            result=ss.toString();
//            in.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//    static class GetDataTask extends TimerTask{
//
//        @Override
//        public void run() {
//            String[] arr = getArr("/home/pi/recipeLab/dht11");
//            sendGet("http://192.168.1.110:8080/getDht11Data?rh="+arr[0]+"&tmp="+arr[1]);
//        }
//    }
}
