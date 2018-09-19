/**   
 * Copyright © 2018 eSunny Info. Developer Stu. All rights reserved.
 * 
 * @Package: com.smxy.recipe.domain 
 * @author: 雏实。   
 * Build File @date: 2018年7月4日 下午1:30:01 
 */
package com.smxy.recipe.entity;

/**
 * @author zpx
 *
 */
public final class Recipe {
	private Integer fId;
	private String fAuthor;
	private String fRelease;
	private String fCover;
	private String fIntroduction;
	private Integer fGood;
	private Integer fCount;
	private Integer fType;
	private String fName;
	private String fFire;
	public Recipe() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Recipe(Integer fId, String fAuthor, String fRelease, String fCover, String fIntroduction, Integer fGood, Integer fCount, Integer fType, String fName, String fFire) {
		this.fId = fId;
		this.fAuthor = fAuthor;
		this.fRelease = fRelease;
		this.fCover = fCover;
		this.fIntroduction = fIntroduction;
		this.fGood = fGood;
		this.fCount = fCount;
		this.fType = fType;
		this.fName = fName;
		this.fFire = fFire;
	}

	public Integer getfId() {
		return fId;
	}

	public void setfId(Integer fId) {
		this.fId = fId;
	}

	public String getfAuthor() {
		return fAuthor;
	}

	public void setfAuthor(String fAuthor) {
		this.fAuthor = fAuthor;
	}

	public String getfRelease() {
		return fRelease;
	}

	public void setfRelease(String fRelease) {
		this.fRelease = fRelease;
	}

	public String getfCover() {
		return fCover;
	}

	public void setfCover(String fCover) {
		this.fCover = fCover;
	}

	public String getfIntroduction() {
		return fIntroduction;
	}

	public void setfIntroduction(String fIntroduction) {
		this.fIntroduction = fIntroduction;
	}

	public Integer getfGood() {
		return fGood;
	}

	public void setfGood(Integer fGood) {
		this.fGood = fGood;
	}

	public Integer getfCount() {
		return fCount;
	}

	public void setfCount(Integer fCount) {
		this.fCount = fCount;
	}

	public Integer getfType() {
		return fType;
	}

	public void setfType(Integer fType) {
		this.fType = fType;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getfFire() {
		return fFire;
	}

	public void setfFire(String fFire) {
		this.fFire = fFire;
	}

	@Override
	public String toString() {
		return "Recipe{" +
				"fId=" + fId +
				", fAuthor='" + fAuthor + '\'' +
				", fRelease='" + fRelease + '\'' +
				", fCover='" + fCover + '\'' +
				", fIntroduction='" + fIntroduction + '\'' +
				", fGood=" + fGood +
				", fCount=" + fCount +
				", fType=" + fType +
				", fName='" + fName + '\'' +
				", fFire='" + fFire + '\'' +
				'}';
	}
}
