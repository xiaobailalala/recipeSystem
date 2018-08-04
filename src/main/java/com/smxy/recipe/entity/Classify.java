/**   
 * Copyright © 2018 eSunny Info. Developer Stu. All rights reserved.
 * 
 * @Package: com.smxy.recipe.domain 
 * @author: 雏实。   
 * Build File @date: 2018年7月4日 下午1:19:40 
 */
package com.smxy.recipe.entity;

/**
 * @author zpx
 *
 */
public class Classify {
	private int f_id;
	private String f_name;
	private String f_introduction;
	public Classify() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Classify(int f_id, String f_name, String f_introduction) {
		super();
		this.f_id = f_id;
		this.f_name = f_name;
		this.f_introduction = f_introduction;
	}
	public int getF_id() {
		return f_id;
	}
	public void setF_id(int f_id) {
		this.f_id = f_id;
	}
	public String getF_name() {
		return f_name;
	}
	public void setF_name(String f_name) {
		this.f_name = f_name;
	}
	public String getF_introduction() {
		return f_introduction;
	}
	public void setF_introduction(String f_introduction) {
		this.f_introduction = f_introduction;
	}
	@Override
	public String toString() {
		return "Classify [f_id=" + f_id + ", f_name=" + f_name + ", f_introduction=" + f_introduction + "]";
	}
}
