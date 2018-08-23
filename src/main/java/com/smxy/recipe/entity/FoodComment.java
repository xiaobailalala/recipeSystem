/**   
 * Copyright © 2018 eSunny Info. Developer Stu. All rights reserved.
 * 
 * @Package: com.smxy.recipe.domain 
 * @author: 雏实。   
 * Build File @date: 2018年7月4日 下午1:23:06 
 */
package com.smxy.recipe.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author zpx
 *
 */
public class FoodComment {
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
	public FoodComment() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FoodComment(Integer fId, Integer fRid, Recipe recipe, Integer fUid, CommonUser commonUser, String fContent, Date fRelease, Integer fGood, String fCover) {
		this.fId = fId;
		this.fRid = fRid;
		this.recipe = recipe;
		this.fUid = fUid;
		this.commonUser = commonUser;
		this.fContent = fContent;
		this.fRelease = fRelease;
		this.fGood = fGood;
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

	public Integer getfUid() {
		return fUid;
	}

	public void setfUid(Integer fUid) {
		this.fUid = fUid;
	}

	public CommonUser getCommonUser() {
		return commonUser;
	}

	public void setCommonUser(CommonUser commonUser) {
		this.commonUser = commonUser;
	}

	public String getfContent() {
		return fContent;
	}

	public void setfContent(String fContent) {
		this.fContent = fContent;
	}

	public Date getfRelease() {
		return fRelease;
	}

	public void setfRelease(Date fRelease) {
		this.fRelease = fRelease;
	}

	public Integer getfGood() {
		return fGood;
	}

	public void setfGood(Integer fGood) {
		this.fGood = fGood;
	}

	public String getfCover() {
		return fCover;
	}

	public void setfCover(String fCover) {
		this.fCover = fCover;
	}

	@Override
	public String toString() {
		return "FoodComment{" +
				"fId=" + fId +
				", fRid=" + fRid +
				", recipe=" + recipe +
				", fUid=" + fUid +
				", commonUser=" + commonUser +
				", fContent='" + fContent + '\'' +
				", fRelease=" + fRelease +
				", fGood=" + fGood +
				", fCover='" + fCover + '\'' +
				'}';
	}
}
