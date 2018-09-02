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
	private String fName;
	private String fBg;
	private String fColor;
	public Tips() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Tips(Integer fId, String fName, String fBg, String fColor) {
		this.fId = fId;
		this.fName = fName;
		this.fBg = fBg;
		this.fColor = fColor;
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

	public String getfBg() {
		return fBg;
	}

	public void setfBg(String fBg) {
		this.fBg = fBg;
	}

	public String getfColor() {
		return fColor;
	}

	public void setfColor(String fColor) {
		this.fColor = fColor;
	}

	@Override
	public String toString() {
		return "Tips{" +
				"fId=" + fId +
				", fName='" + fName + '\'' +
				", fBg='" + fBg + '\'' +
				", fColor='" + fColor + '\'' +
				'}';
	}
}
