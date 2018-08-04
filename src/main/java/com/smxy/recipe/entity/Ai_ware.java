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
public class Ai_ware {
	private int f_id;
	private String f_content;
	private String f_word;
	public Ai_ware() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Ai_ware(int f_id, String f_content, String f_word) {
		super();
		this.f_id = f_id;
		this.f_content = f_content;
		this.f_word = f_word;
	}
	public int getF_id() {
		return f_id;
	}
	public void setF_id(int f_id) {
		this.f_id = f_id;
	}
	public String getF_content() {
		return f_content;
	}
	public void setF_content(String f_content) {
		this.f_content = f_content;
	}
	public String getF_word() {
		return f_word;
	}
	public void setF_word(String f_word) {
		this.f_word = f_word;
	}
	@Override
	public String toString() {
		return "Ai_ware [f_id=" + f_id + ", f_content=" + f_content + ", f_word=" + f_word + "]";
	}
}
