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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class test {
    public static void main(String[] args) throws IOException {
        InputStream fis = null;
        Process process = Runtime.getRuntime().exec("/home/pi/recipeLab/dht11");
        fis = process.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fis));
        String s;
        StringBuffer sb = new StringBuffer();
        while((s=bufferedReader.readLine())!=null){
            sb.append(s);
        }
        String arr[]=sb.toString().split(" ");
        String result="";
        String line;
        BufferedReader in = null;
        String urlNameString = "http://192.168.1.110:8080/getDht11Data?rh="+arr[0]+"&tmp="+arr[1];
        URL realUrl = new URL(urlNameString);
        URLConnection conn = realUrl.openConnection();
        conn.setRequestProperty("contentType","utf-8");
        conn.setRequestProperty("content-type","application/x-www-form-urlencoded");
        conn.setConnectTimeout(60);
        conn.setReadTimeout(60);
        conn.connect();
        in = new BufferedReader(new InputStreamReader(
                conn.getInputStream(),"utf-8"));
        StringBuffer ss = new StringBuffer();
        while ((line = in.readLine()) != null) {
            ss.append(line);
        }
        result=ss.toString();
        in.close();
    }
}
