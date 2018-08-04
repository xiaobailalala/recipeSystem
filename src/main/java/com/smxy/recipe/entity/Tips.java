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
	private int f_id;
	private int f_rid;
	private Recipe recipe;
	private String f_content;
	private int f_pid;
	private Common_product common_product;
	public Tips() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Tips(int f_id, int f_rid, Recipe recipe, String f_content, int f_pid, Common_product common_product) {
		super();
		this.f_id = f_id;
		this.f_rid = f_rid;
		this.recipe = recipe;
		this.f_content = f_content;
		this.f_pid = f_pid;
		this.common_product = common_product;
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
	public String getF_content() {
		return f_content;
	}
	public void setF_content(String f_content) {
		this.f_content = f_content;
	}
	public int getF_pid() {
		return f_pid;
	}
	public void setF_pid(int f_pid) {
		this.f_pid = f_pid;
	}
	public Common_product getCommon_product() {
		return common_product;
	}
	public void setCommon_product(Common_product common_product) {
		this.common_product = common_product;
	}
	@Override
	public String toString() {
		return "Tips [f_id=" + f_id + ", f_rid=" + f_rid + ", recipe=" + recipe + ", f_content=" + f_content
				+ ", f_pid=" + f_pid + ", common_product=" + common_product + "]";
	}
}
