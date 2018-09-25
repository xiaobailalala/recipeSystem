/**
 * Copyright © 2018 eSunny Info. Developer Stu. All rights reserved.
 *
 * @Package:
 * @author: zpx
 * Build File @date: 2018/9/25 0:40
 * @Description TODO
 * @version 1.0
 */
package com.smxy.recipe.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.smxy.recipe.config.YmlConfig;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.Map;

public class YmlConfigUtil implements ApplicationContextAware {
    private static ApplicationContext applicationContext = null;

    private static Map<String, String> propertiesMap = null;

    public YmlConfigUtil() {
    }

    public static String getConfigByKey(String key) throws JsonProcessingException {
        if (propertiesMap == null) {
            YmlConfig ymlConfig = applicationContext.getBean(YmlConfig.class);
            propertiesMap = ymlConfig.getMapProps();
        }
        return propertiesMap.get(key);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (YmlConfigUtil.applicationContext == null) {
            YmlConfigUtil.applicationContext = applicationContext;
        }
    }
}