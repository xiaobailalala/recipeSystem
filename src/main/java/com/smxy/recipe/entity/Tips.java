/**   
 * Copyright © 2018 eSunny Info. Developer Stu. All rights reserved.
 * 
 * @Package: com.smxy.recipe.domain 
 * @author: 雏实。   
 * Build File @date: 2018年7月4日 下午1:30:31 
 */
package com.smxy.recipe.entity;

/**
 * @author zpx
 *
 */
public class Tips {
	private Integer fId;
	private Integer fRid;
	private Recipe recipe;
	private String fContent;
	private Integer fPid;
	private CommonProduct commonProduct;
	public Tips() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Tips(Integer fId, Integer fRid, Recipe recipe, String fContent, Integer fPid, CommonProduct commonProduct) {
		this.fId = fId;
		this.fRid = fRid;
		this.recipe = recipe;
		this.fContent = fContent;
		this.fPid = fPid;
		this.commonProduct = commonProduct;
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

	public String getfContent() {
		return fContent;
	}

	public void setfContent(String fContent) {
		this.fContent = fContent;
	}

	public Integer getfPid() {
		return fPid;
	}

	public void setfPid(Integer fPid) {
		this.fPid = fPid;
	}

	public CommonProduct getCommonProduct() {
		return commonProduct;
	}

	public void setCommonProduct(CommonProduct commonProduct) {
		this.commonProduct = commonProduct;
	}

	@Override
	public String toString() {
		return "Tips{" +
				"fId=" + fId +
				", fRid=" + fRid +
				", recipe=" + recipe +
				", fContent='" + fContent + '\'' +
				", fPid=" + fPid +
				", commonProduct=" + commonProduct +
				'}';
	}
}
