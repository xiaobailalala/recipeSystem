/**   
 * Copyright © 2018 eSunny Info. Developer Stu. All rights reserved.
 * 
 * @Package: com.smxy.recipe.domain 
 * @author: 雏实。   
 * Build File @date: 2018年7月4日 下午1:20:19 
 */
package com.smxy.recipe.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author zpx
 *
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public final class Collect {
	private Integer fId;
	private Integer fUid;
	private Integer fRid;
	private Integer fType;
	private CommonUser commonUser;
	private Recipe recipe;
	private Article article;

	public Collect(Integer fUid, Integer fRid, Integer fType) {
		this.fUid = fUid;
		this.fRid = fRid;
		this.fType = fType;
	}
}
