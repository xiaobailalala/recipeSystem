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
public final class Tips {
	private Integer fId;
	private String fName;
	private String fStyle;
	public Tips() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Tips(Integer fId, String fName, String fStyle) {
		this.fId = fId;
		this.fName = fName;
		this.fStyle = fStyle;
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

	public String getfStyle() {
		return fStyle;
	}

	public void setfStyle(String fStyle) {
		this.fStyle = fStyle;
	}

	@Override
	public String toString() {
		return "Tips{" +
				"fId=" + fId +
				", fName='" + fName + '\'' +
				", fStyle='" + fStyle + '\'' +
				'}';
	}
}
