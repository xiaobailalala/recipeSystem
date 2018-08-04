/**   
 * Copyright © 2018 eSunny Info. Developer Stu. All rights reserved.
 * 
 * @Package: com.smxy.recipe.serviceImpl 
 * @author: 雏实。   
 * Build File @date: 2018年7月4日 下午1:34:35 
 */
package com.smxy.recipe.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smxy.recipe.dao.ProfessionDao;
import com.smxy.recipe.entity.Profession;
import com.smxy.recipe.service.ProfessionService;
import com.smxy.recipe.utils.ResApi;

/**
 * @author zpx
 *
 */
@Service("professionService")
public class ProfessionServiceImpl implements ProfessionService {
	
	@Autowired
	ProfessionDao professionDao;

	@Override
	public ResApi<List<Profession>> getAllInfo() {
		// TODO Auto-generated method stub
		return new ResApi<>(200,"success",professionDao.findAll());
	}

}
