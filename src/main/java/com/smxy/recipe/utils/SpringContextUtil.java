/**   
 * Copyright © 2018 eSunny Info. Developer Stu. All rights reserved.
 * 
 * @Package: com.smxy.recipe.utils 
 * @author: 雏实。   
 * Build File @date: 2018年7月4日 下午1:38:07 
 */
package com.smxy.recipe.utils;

/**
 * @author zpx
 *
 */
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class SpringContextUtil implements ApplicationContextAware {

	private static ApplicationContext applicationContext = null;
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		// TODO Auto-generated method stub
		this.applicationContext = applicationContext;
	}
	
	public static ApplicationContext  getApplicationContext(){
        return applicationContext;
    }
	
	public static Object getBean(String beanName){
        return applicationContext.getBean(beanName);
    }

	public static Object getBean(Class c){
		return applicationContext.getBean(c);
	}

}

