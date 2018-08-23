/**   
 * Copyright © 2018 eSunny Info. Developer Stu. All rights reserved.
 * 
 * @Package: com.smxy.recipe.domain 
 * @author: 雏实。   
 * Build File @date: 2018年7月4日 下午1:19:40 
 */
package com.smxy.recipe.entity;

/**
 * @author zpx
 *
 */
public class Classify {
	private Integer fId;
	private String fName;
	private String fIntroduction;
	public Classify() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Classify(Integer fId, String fName, String fIntroduction) {
		this.fId = fId;
		this.fName = fName;
		this.fIntroduction = fIntroduction;
	}

	public Integer getfId() {
		return fId;
	}

	public void setfId(Integer fId) {
		this.fId = fId;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getfIntroduction() {
		return fIntroduction;
	}

	public void setfIntroduction(String fIntroduction) {
		this.fIntroduction = fIntroduction;
	}

	@Override
	public String toString() {
		return "Classify{" +
				"fId=" + fId +
				", fName='" + fName + '\'' +
				", fIntroduction='" + fIntroduction + '\'' +
				'}';
	}
}
