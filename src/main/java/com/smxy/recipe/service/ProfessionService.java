/**   
 * Copyright © 2018 eSunny Info. Developer Stu. All rights reserved.
 * 
 * @Package: com.smxy.recipe.service 
 * @author: 雏实。   
 * Build File @date: 2018年7月4日 下午1:32:17 
 */
package com.smxy.recipe.service;

import java.util.List;

import com.smxy.recipe.entity.Profession;
import com.smxy.recipe.utils.ResApi;

/**
 * @author zpx
 *
 */
public interface ProfessionService {
	public ResApi<List<Profession>> getAllInfo();
}
