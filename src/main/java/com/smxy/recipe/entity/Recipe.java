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
public class Recipe {
	private Integer fId;
	private Integer fCid;
	private Classify classify;
	private String fAuthor;
	private String fRelease;
	private String fCover;
	private String fIntroduction;
	private Integer fGood;
	private Integer fCount;
	private Integer fType;
	private Integer fReady;
	public Recipe() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Recipe(Integer fId, Integer fCid, Classify classify, String fAuthor, String fRelease, String fCover, String fIntroduction, Integer fGood, Integer fCount, Integer fType, Integer fReady) {
		this.fId = fId;
		this.fCid = fCid;
		this.classify = classify;
		this.fAuthor = fAuthor;
		this.fRelease = fRelease;
		this.fCover = fCover;
		this.fIntroduction = fIntroduction;
		this.fGood = fGood;
		this.fCount = fCount;
		this.fType = fType;
		this.fReady = fReady;
	}

	public Integer getfId() {
		return fId;
	}

	public void setfId(Integer fId) {
		this.fId = fId;
	}

	public Integer getfCid() {
		return fCid;
	}

	public void setfCid(Integer fCid) {
		this.fCid = fCid;
	}

	public Classify getClassify() {
		return classify;
	}

	public void setClassify(Classify classify) {
		this.classify = classify;
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

	public Integer getfReady() {
		return fReady;
	}

	public void setfReady(Integer fReady) {
		this.fReady = fReady;
	}

	@Override
	public String toString() {
		return "Recipe{" +
				"fId=" + fId +
				", fCid=" + fCid +
				", classify=" + classify +
				", fAuthor='" + fAuthor + '\'' +
				", fRelease='" + fRelease + '\'' +
				", fCover='" + fCover + '\'' +
				", fIntroduction='" + fIntroduction + '\'' +
				", fGood=" + fGood +
				", fCount=" + fCount +
				", fType=" + fType +
				", fReady=" + fReady +
				'}';
	}
}
