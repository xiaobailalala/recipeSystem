/**   
 * Copyright © 2018 eSunny Info. Developer Stu. All rights reserved.
 * 
 * @Package: com.smxy.recipe.domain 
 * @author: 雏实。   
 * Build File @date: 2018年7月4日 下午1:23:06 
 */
package com.smxy.recipe.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author zpx
 *
 */
public class Food_comment {
	private int f_id;
	private int f_rid;
	private Recipe recipe;
	private int f_uid;
	private Common_user common_user;
	private String f_content;
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date f_release;
	private int f_good;
	private String f_cover;
	public Food_comment() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Food_comment(int f_id, int f_rid, Recipe recipe, int f_uid, Common_user common_user, String f_content,
			Date f_release, int f_good, String f_cover) {
		super();
		this.f_id = f_id;
		this.f_rid = f_rid;
		this.recipe = recipe;
		this.f_uid = f_uid;
		this.common_user = common_user;
		this.f_content = f_content;
		this.f_release = f_release;
		this.f_good = f_good;
		this.f_cover = f_cover;
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
	public int getF_uid() {
		return f_uid;
	}
	public void setF_uid(int f_uid) {
		this.f_uid = f_uid;
	}
	public Common_user getCommon_user() {
		return common_user;
	}
	public void setCommon_user(Common_user common_user) {
		this.common_user = common_user;
	}
	public String getF_content() {
		return f_content;
	}
	public void setF_content(String f_content) {
		this.f_content = f_content;
	}
	public Date getF_release() {
		return f_release;
	}
	public void setF_release(Date f_release) {
		this.f_release = f_release;
	}
	public int getF_good() {
		return f_good;
	}
	public void setF_good(int f_good) {
		this.f_good = f_good;
	}
	public String getF_cover() {
		return f_cover;
	}
	public void setF_cover(String f_cover) {
		this.f_cover = f_cover;
	}
	@Override
	public String toString() {
		return "Food_comment [f_id=" + f_id + ", f_rid=" + f_rid + ", recipe=" + recipe + ", f_uid=" + f_uid
				+ ", common_user=" + common_user + ", f_content=" + f_content + ", f_release=" + f_release + ", f_good="
				+ f_good + ", f_cover=" + f_cover + "]";
	}
}
