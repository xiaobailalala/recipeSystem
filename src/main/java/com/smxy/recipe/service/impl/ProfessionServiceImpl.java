/**   
 * Copyright © 2018 eSunny Info. Developer Stu. All rights reserved.
 * 
 * @Package: com.smxy.recipe.serviceImpl 
 * @author: 雏实。   
 * Build File @date: 2018年7月4日 下午1:34:35 
 */
package com.smxy.recipe.service.impl;

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
	private ProfessionDao professionDao;

	@Override
	public ResApi<List<Profession>> getAllInfo() {
		// TODO Auto-generated method stub
		return new ResApi<>(200,"success",professionDao.findAll());
	}

	@Override
	public ResApi<Object> deleteInfo(Integer id) {
		ResApi<Object> resApi=new ResApi<>(500,"系统出错。","error");
		if (professionDao.deleteInfo(id)>0){
			resApi=new ResApi<>(200,"success","success");
		}
		return resApi;
	}

	@Override
	public ResApi<Object> saveInfo(Profession profession) {
        ResApi<Object> resApi;
        if (professionDao.getPorfessByName(profession.getFName())!=null){
            resApi=new ResApi<>(501,"该职业已存在，请勿重复添加","failed");
        }else{
            if (professionDao.saveInfo(profession)>0){
                resApi=new ResApi<>(200,"success","success");
            }else{
                resApi=new ResApi<>(502,"添加失败。","failed");
            }
        }
        return resApi;
	}

    @Override
    public ResApi<Object> updateInfo(Integer id, Profession profession) {
	    profession.setFId(id);
        ResApi<Object> resApi=new ResApi<>(500,"系统出错。","error");
        if (professionDao.updateInfo(profession)>0){
            resApi=new ResApi<>(200,"success","success");
        }
        return resApi;
    }

    @Override
    public Profession getOneById(Integer id) {
        return professionDao.getOneById(id);
    }

}
