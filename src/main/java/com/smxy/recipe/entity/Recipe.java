/**   
 * Copyright © 2018 eSunny Info. Developer Stu. All rights reserved.
 * 
 * @Package: com.smxy.recipe.domain 
 * @author: 雏实。   
 * Build File @date: 2018年7月4日 下午1:30:01 
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
public final class Recipe {
	private Integer fId;
	private Integer fAid;
	private Integer fUid;
	private AdminUser adminUser;
	private CommonUser commonUser;
	private String fRelease;
	private String fCover;
	private String fIntroduction;
	private Integer fGood;
	private Integer fCount;
	private String fType;
	private String fName;
	private String fFire;
	private List<RecipeClassify> recipeClassifies;
	private List<RecipeTips> recipeTips;
	private List<RecipeMaterial> recipeMaterials;
	private List<Process> processes;
}
