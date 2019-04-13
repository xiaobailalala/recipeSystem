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
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author zpx
 *
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public final class Recipe {
	private Integer fId;
	private Integer fUid;
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
	private Integer commentCount;
}
