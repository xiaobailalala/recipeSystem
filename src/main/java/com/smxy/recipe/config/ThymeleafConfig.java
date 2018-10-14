/**
 * Copyright © 2018 eSunny Info. Developer Stu. All rights reserved.
 *
 * @Package:
 * @author: zpx
 * Build File @date: 2018/8/9 21:56
 * @Description TODO
 * @version 1.0
 */
package com.smxy.recipe.config;

import org.springframework.context.annotation.Configuration;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Configuration
//@Lazy(false)
public class ThymeleafConfig {

//    @Value("${ServerHostIp}")
    private String serverHost="192.168.1.108";
//    private String serverHost="172.21.91.21";
//    private String serverHost="192.168.0.108";

    @Resource
    private void configureThymeleafStaticVars(ThymeleafViewResolver viewResolver) {
//        System.out.println("---->"+serverHost);
//        try {
//            System.out.println(YmlConfigUtil.getConfigByKey("ServerHostIp"));
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }
        if(viewResolver != null) {
            Map<String, Object> vars = new HashMap<>();
//            vars.put("fileServerPath", "http://192.168.1.108/");
            vars.put("fileServerPath", "http://"+serverHost+"/");
            viewResolver.setStaticVariables(vars);
        }
    }

//    @Bean
//    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
//        return new PropertySourcesPlaceholderConfigurer();
//    }

}
