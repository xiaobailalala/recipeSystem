/**   
 * Copyright © 2018 eSunny Info. Developer Stu. All rights reserved.
 * 
 * @Package: com.smxy.recipe.domain 
 * @author: 雏实。   
 * Build File @date: 2018年7月4日 下午1:22:24 
 */
package com.smxy.recipe.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author zpx
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public final class CommonUser {
	private Integer fId;
	private String fAccount;
	private String fUsername;
	private String fPassword;
	private String fProvince="北京市";
	private String fCity="北京市";
	private String fArea="东城区";
	private String fCover;
	private String fSex="男";
	private String fSign;
	private Integer fPid=0;
	private String fBg;
	private Profession profession;
	private Integer collectCount;
	private List<Recipe> recipes;
	private List<Article> articles;
}
