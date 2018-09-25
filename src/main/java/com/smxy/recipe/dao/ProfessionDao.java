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

	List<Profession> findAll();

	Integer deleteInfo(Integer fId);

	Profession getPorfessByName(String fName);

	Integer saveInfo(Profession profession);

	Integer updateInfo(Profession profession);

	Profession getOneById(Integer fId);
	
}
