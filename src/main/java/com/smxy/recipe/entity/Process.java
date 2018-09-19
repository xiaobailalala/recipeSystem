/**   
 * Copyright © 2018 eSunny Info. Developer Stu. All rights reserved.
 * 
 * @Package: com.smxy.recipe.domain 
 * @author: 雏实。   
 * Build File @date: 2018年7月4日 下午1:28:55 
 */
package com.smxy.recipe.entity;

/**
 * @author zpx
 *
 */
public final class Process {
	private Integer fId;
	private Integer fRid;
	private Recipe recipe;
	private String fContent;
	private String fRequest;
	private String fCover;
	public Process() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Process(Integer fRid, String fContent, String fRequest, String fCover) {
		this.fRid = fRid;
		this.fContent = fContent;
		this.fRequest = fRequest;
		this.fCover = fCover;
	}

	public Process(Integer fId, Integer fRid, Recipe recipe, String fContent, String fRequest, String fCover) {
		this.fId = fId;
		this.fRid = fRid;
		this.recipe = recipe;
		this.fContent = fContent;
		this.fRequest = fRequest;
		this.fCover = fCover;
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

	public String getfRequest() {
		return fRequest;
	}

	public void setfRequest(String fRequest) {
		this.fRequest = fRequest;
	}

	public String getfCover() {
		return fCover;
	}

	public void setfCover(String fCover) {
		this.fCover = fCover;
	}

	@Override
	public String toString() {
		return "Process{" +
				"fId=" + fId +
				", fRid=" + fRid +
				", recipe=" + recipe +
				", fContent='" + fContent + '\'' +
				", fRequest='" + fRequest + '\'' +
				", fCover='" + fCover + '\'' +
				'}';
	}
}
