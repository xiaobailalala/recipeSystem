/**   
 * Copyright © 2018 eSunny Info. Developer Stu. All rights reserved.
 * 
 * @Package: com.smxy.recipe.domain 
 * @author: 雏实。   
 * Build File @date: 2018年7月4日 下午1:19:40 
 */
package com.smxy.recipe.entity;

import java.io.Serializable;

/**
 * @author zpx
 *
 */
public class Classify implements Serializable {
	private Integer fId;
	private String fName;
	private Integer fTid;
	private ClassifyTwo classifyTwo;
	public Classify() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Classify(Integer fId, String fName, Integer fTid, ClassifyTwo classifyTwo) {
		this.fId = fId;
		this.fName = fName;
		this.fTid = fTid;
		this.classifyTwo = classifyTwo;
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

	public Integer getfTid() {
		return fTid;
	}

	public void setfTid(Integer fTid) {
		this.fTid = fTid;
	}

	public ClassifyTwo getClassifyTwo() {
		return classifyTwo;
	}

	public void setClassifyTwo(ClassifyTwo classifyTwo) {
		this.classifyTwo = classifyTwo;
	}

	@Override
	public String toString() {
		return "Classify{" +
				"fId=" + fId +
				", fName='" + fName + '\'' +
				", fTid=" + fTid +
				", classifyTwo=" + classifyTwo +
				'}';
	}
}
