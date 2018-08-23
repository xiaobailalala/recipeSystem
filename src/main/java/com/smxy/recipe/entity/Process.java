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
public class Process {
	private Integer fId;
	private Integer fRid;
	private Recipe recipe;
	private Integer fNum;
	private String fContent;
	private Integer fRequest;
	private String fAiContent;
	public Process() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Process(Integer fId, Integer fRid, Recipe recipe, Integer fNum, String fContent, Integer fRequest, String fAiContent) {
		this.fId = fId;
		this.fRid = fRid;
		this.recipe = recipe;
		this.fNum = fNum;
		this.fContent = fContent;
		this.fRequest = fRequest;
		this.fAiContent = fAiContent;
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

	public Integer getfNum() {
		return fNum;
	}

	public void setfNum(Integer fNum) {
		this.fNum = fNum;
	}

	public String getfContent() {
		return fContent;
	}

	public void setfContent(String fContent) {
		this.fContent = fContent;
	}

	public Integer getfRequest() {
		return fRequest;
	}

	public void setfRequest(Integer fRequest) {
		this.fRequest = fRequest;
	}

	public String getfAiContent() {
		return fAiContent;
	}

	public void setfAiContent(String fAiContent) {
		this.fAiContent = fAiContent;
	}

	@Override
	public String toString() {
		return "Process{" +
				"fId=" + fId +
				", fRid=" + fRid +
				", recipe=" + recipe +
				", fNum=" + fNum +
				", fContent='" + fContent + '\'' +
				", fRequest=" + fRequest +
				", fAiContent='" + fAiContent + '\'' +
				'}';
	}
}
