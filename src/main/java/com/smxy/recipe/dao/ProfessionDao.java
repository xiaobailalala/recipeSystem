/**   
 * Copyright © 2018 eSunny Info. Developer Stu. All rights reserved.
 * 
 * @Package: com.smxy.recipe.dao 
 * @author: 雏实。   
 * Build File @date: 2018年7月4日 下午1:17:12 
 */
package com.smxy.recipe.dao;

/**
 * @author zpx
 *
 */
import java.util.List;

import com.smxy.recipe.entity.Profession;

public interface ProfessionDao {

	public List<Profession> findAll();

	public int deleteInfo(Integer fId);

	public Profession getPorfessByName(String fName);

	public int saveInfo(Profession profession);

	public int updateInfo(Profession profession);

	public Profession getOneById(Integer fId);
	
}
