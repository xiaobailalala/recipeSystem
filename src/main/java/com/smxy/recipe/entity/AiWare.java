/**   
 * Copyright © 2018 eSunny Info. Developer Stu. All rights reserved.
 * 
 * @Package: com.smxy.recipe.domain 
 * @author: 雏实。   
 * Build File @date: 2018年7月4日 下午1:18:28 
 */
package com.smxy.recipe.entity;

/**
 * @author zpx
 *
 */
public class AiWare {
	private Integer fId;
	private String fContent;
	private String fWord;
	public AiWare() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AiWare(Integer fId, String fContent, String fWord) {
		this.fId = fId;
		this.fContent = fContent;
		this.fWord = fWord;
	}

	public Integer getfId() {
		return fId;
	}

	public void setfId(Integer fId) {
		this.fId = fId;
	}

	public String getfContent() {
		return fContent;
	}

	public void setfContent(String fContent) {
		this.fContent = fContent;
	}

	public String getfWord() {
		return fWord;
	}

	public void setfWord(String fWord) {
		this.fWord = fWord;
	}

	@Override
	public String toString() {
		return "AiWare{" +
				"fId=" + fId +
				", fContent='" + fContent + '\'' +
				", fWord='" + fWord + '\'' +
				'}';
	}
}
