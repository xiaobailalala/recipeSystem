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
	private int f_id;
	private int f_rid;
	private Recipe recipe;
	private int f_num;
	private String f_content;
	private int f_request;
	private String f_ai_content;
	public Process() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Process(int f_id, int f_rid, Recipe recipe, int f_num, String f_content, int f_request,
			String f_ai_content) {
		super();
		this.f_id = f_id;
		this.f_rid = f_rid;
		this.recipe = recipe;
		this.f_num = f_num;
		this.f_content = f_content;
		this.f_request = f_request;
		this.f_ai_content = f_ai_content;
	}
	public int getF_id() {
		return f_id;
	}
	public void setF_id(int f_id) {
		this.f_id = f_id;
	}
	public int getF_rid() {
		return f_rid;
	}
	public void setF_rid(int f_rid) {
		this.f_rid = f_rid;
	}
	public Recipe getRecipe() {
		return recipe;
	}
	public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
	}
	public int getF_num() {
		return f_num;
	}
	public void setF_num(int f_num) {
		this.f_num = f_num;
	}
	public String getF_content() {
		return f_content;
	}
	public void setF_content(String f_content) {
		this.f_content = f_content;
	}
	public int getF_request() {
		return f_request;
	}
	public void setF_request(int f_request) {
		this.f_request = f_request;
	}
	public String getF_ai_content() {
		return f_ai_content;
	}
	public void setF_ai_content(String f_ai_content) {
		this.f_ai_content = f_ai_content;
	}
	@Override
	public String toString() {
		return "Process [f_id=" + f_id + ", f_rid=" + f_rid + ", recipe=" + recipe + ", f_num=" + f_num + ", f_content="
				+ f_content + ", f_request=" + f_request + ", f_ai_content=" + f_ai_content + "]";
	}
}
