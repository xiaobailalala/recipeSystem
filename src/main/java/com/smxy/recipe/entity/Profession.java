/**   
 * Copyright © 2018 eSunny Info. Developer Stu. All rights reserved.
 * 
 * @Package: com.smxy.recipe.domain 
 * @author: 雏实。   
 * Build File @date: 2018年7月4日 下午1:29:34 
 */
package com.smxy.recipe.entity;

/**
 * @author zpx
 *
 */
public class Profession {
	private Integer f_id;
	private String f_name;
	public Profession() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Profession(Integer f_id, String f_name) {
		super();
		this.f_id = f_id;
		this.f_name = f_name;
	}
	public Integer getF_id() {
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
	@Override
	public String toString() {
		return "Common_profession [f_id=" + f_id + ", f_name=" + f_name + "]";
	}
}
