/**   
 * Copyright © 2018 eSunny Info. Developer Stu. All rights reserved.
 * 
 * @Package: com.smxy.recipe.domain 
 * @author: 雏实。   
 * Build File @date: 2018年7月4日 下午1:23:06 
 */
package com.smxy.recipe.entity;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author zpx
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public final class FoodComment {
	private Integer fId;
	private Integer fRid;
	private Recipe recipe;
	private Integer fUid;
	private CommonUser commonUser;
	private String fContent;
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date fRelease;
	private Integer fGood;
	private String fCover;
}
