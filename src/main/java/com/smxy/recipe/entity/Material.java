/**   
 * Copyright © 2018 eSunny Info. Developer Stu. All rights reserved.
 * 
 * @Package: com.smxy.recipe.domain 
 * @author: 雏实。   
 * Build File @date: 2018年7月4日 下午1:24:07 
 */
package com.smxy.recipe.entity;

/**
 * @author zpx
 *
 */
public class Material {
	private Integer fId;
	private Integer fRid;
	private Recipe recipe;
	private String fName;
	public Material() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Material(Integer fId, Integer fRid, Recipe recipe, String fName) {
		this.fId = fId;
		this.fRid = fRid;
		this.recipe = recipe;
		this.fName = fName;
	}

	public Integer getfId() {
		return fId;
	}

	public void setfId(Integer fId) {
		this.fId = fId;
	}

	public Integer getfRid() {
		return fRid;
	}

	public void setfRid(Integer fRid) {
		this.fRid = fRid;
	}

	public Recipe getRecipe() {
		return recipe;
	}

	public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	@Override
	public String toString() {
		return "Material{" +
				"fId=" + fId +
				", fRid=" + fRid +
				", recipe=" + recipe +
				", fName='" + fName + '\'' +
				'}';
	}
}
