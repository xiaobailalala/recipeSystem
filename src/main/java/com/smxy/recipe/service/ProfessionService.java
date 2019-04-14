/**   
 * Copyright © 2018 eSunny Info. Developer Stu. All rights reserved.
 * 
 * @Package: com.smxy.recipe.service 
 * @author: 雏实。   
 * Build File @date: 2018年7月4日 下午1:32:17 
 */
package com.smxy.recipe.service;

import com.smxy.recipe.entity.Profession;
import com.smxy.recipe.utils.ResApi;

import java.util.List;

/**
 * @author zpx
 *
 */
public interface ProfessionService {
	ResApi<List<Profession>> getAllInfo();
	ResApi<Object> deleteInfo(Integer id);
	ResApi<Object> saveInfo(Profession profession);
	ResApi<Object> updateInfo(Integer id,Profession profession);
	Profession getOneById(Integer id);
}
