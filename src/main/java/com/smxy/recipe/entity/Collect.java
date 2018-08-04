/**   
 * Copyright © 2018 eSunny Info. Developer Stu. All rights reserved.
 * 
 * @Package: com.smxy.recipe.domain 
 * @author: 雏实。   
 * Build File @date: 2018年7月4日 下午1:20:19 
 */
package com.smxy.recipe.entity;

/**
 * @author zpx
 *
 */
public class Collect {
	private int f_id;
	private int f_uid;
	private int f_rid;
	private Common_user common_user;
	private Recipe recipe;
	public Collect() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Collect(int f_id, int f_uid, int f_rid, Common_user common_user, Recipe recipe) {
		super();
		this.f_id = f_id;
		this.f_uid = f_uid;
		this.f_rid = f_rid;
		this.common_user = common_user;
		this.recipe = recipe;
	}
	public int getF_id() {
		return f_id;
	}
	public void setF_id(int f_id) {
		this.f_id = f_id;
	}
	public int getF_uid() {
		return f_uid;
	}
	public void setF_uid(int f_uid) {
		this.f_uid = f_uid;
	}
	public int getF_rid() {
		return f_rid;
	}
	public void setF_rid(int f_rid) {
		this.f_rid = f_rid;
	}
	public Common_user getCommon_user() {
		return common_user;
	}
	public void setCommon_user(Common_user common_user) {
		this.common_user = common_user;
	}
	public Recipe getRecipe() {
		return recipe;
	}
	public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
	}
	@Override
	public String toString() {
		return "Collect [f_id=" + f_id + ", f_uid=" + f_uid + ", f_rid=" + f_rid + ", common_user=" + common_user
				+ ", recipe=" + recipe + "]";
	}
}
