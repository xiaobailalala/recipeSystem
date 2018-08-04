/**   
 * Copyright © 2018 eSunny Info. Developer Stu. All rights reserved.
 * 
 * @Package: com.smxy.recipe.domain 
 * @author: 雏实。   
 * Build File @date: 2018年7月4日 下午1:30:01 
 */
package com.smxy.recipe.entity;

/**
 * @author zpx
 *
 */
public class Recipe {
	private int f_id;
	private int f_cid;
	private Classify classify;
	private String f_author;
	private String f_release;
	private String f_cover;
	private String f_introduction;
	private int f_good;
	private int f_count;
	private int type;
	private int ready;
	public Recipe() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Recipe(int f_id, int f_cid, Classify classify, String f_author, String f_release, String f_cover,
			String f_introduction, int f_good, int f_count, int type, int ready) {
		super();
		this.f_id = f_id;
		this.f_cid = f_cid;
		this.classify = classify;
		this.f_author = f_author;
		this.f_release = f_release;
		this.f_cover = f_cover;
		this.f_introduction = f_introduction;
		this.f_good = f_good;
		this.f_count = f_count;
		this.type = type;
		this.ready = ready;
	}
	public int getF_id() {
		return f_id;
	}
	public void setF_id(int f_id) {
		this.f_id = f_id;
	}
	public int getF_cid() {
		return f_cid;
	}
	public void setF_cid(int f_cid) {
		this.f_cid = f_cid;
	}
	public Classify getClassify() {
		return classify;
	}
	public void setClassify(Classify classify) {
		this.classify = classify;
	}
	public String getF_author() {
		return f_author;
	}
	public void setF_author(String f_author) {
		this.f_author = f_author;
	}
	public String getF_release() {
		return f_release;
	}
	public void setF_release(String f_release) {
		this.f_release = f_release;
	}
	public String getF_cover() {
		return f_cover;
	}
	public void setF_cover(String f_cover) {
		this.f_cover = f_cover;
	}
	public String getF_introduction() {
		return f_introduction;
	}
	public void setF_introduction(String f_introduction) {
		this.f_introduction = f_introduction;
	}
	public int getF_good() {
		return f_good;
	}
	public void setF_good(int f_good) {
		this.f_good = f_good;
	}
	public int getF_count() {
		return f_count;
	}
	public void setF_count(int f_count) {
		this.f_count = f_count;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getReady() {
		return ready;
	}
	public void setReady(int ready) {
		this.ready = ready;
	}
	@Override
	public String toString() {
		return "Recipe [f_id=" + f_id + ", f_cid=" + f_cid + ", classify=" + classify + ", f_author=" + f_author
				+ ", f_release=" + f_release + ", f_cover=" + f_cover + ", f_introduction=" + f_introduction
				+ ", f_good=" + f_good + ", f_count=" + f_count + ", type=" + type + ", ready=" + ready + "]";
	}
}
