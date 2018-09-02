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

public class test {
    public static void main(String[] args) throws IOException {
        InputStream fis = null;
        Process process = Runtime.getRuntime().exec("dht11");
        fis = process.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fis));
        String s;
        StringBuffer sb = new StringBuffer();
        while((s=bufferedReader.readLine())!=null){
            sb.append(s);
        }
        System.out.println(sb.toString());
    }
}
