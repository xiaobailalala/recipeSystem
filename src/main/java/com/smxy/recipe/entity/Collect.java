/**   
 * Copyright © 2018 eSunny Info. Developer Stu. All rights reserved.
 * 
 * @Package: com.smxy.recipe.domain 
 * @author: 雏实。   
 * Build File @date: 2018年7月4日 下午1:20:19 
 */
package com.smxy.recipe.entity;

/**
 * @author zpx
 *
 */
public final class Collect {
	private Integer fId;
	private Integer fUid;
	private Integer fRid;
	private CommonUser commonUser;
	private Recipe recipe;
	public Collect() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Collect(Integer fId, Integer fUid, Integer fRid, CommonUser commonUser, Recipe recipe) {
		this.fId = fId;
		this.fUid = fUid;
		this.fRid = fRid;
		this.commonUser = commonUser;
		this.recipe = recipe;
	}

	public Integer getfId() {
		return fId;
	}

	public void setfId(Integer fId) {
		this.fId = fId;
	}

	public Integer getfUid() {
		return fUid;
	}

	public void setfUid(Integer fUid) {
		this.fUid = fUid;
	}

	public Integer getfRid() {
		return fRid;
	}

	public void setfRid(Integer fRid) {
		this.fRid = fRid;
	}

	public CommonUser getCommonUser() {
		return commonUser;
	}

	public void setCommonUser(CommonUser commonUser) {
		this.commonUser = commonUser;
	}

	public Recipe getRecipe() {
		return recipe;
	}

	public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
	}

	@Override
	public String toString() {
		return "Collect{" +
				"fId=" + fId +
				", fUid=" + fUid +
				", fRid=" + fRid +
				", commonUser=" + commonUser +
				", recipe=" + recipe +
				'}';
	}
}
