/**   
 * Copyright © 2018 eSunny Info. Developer Stu. All rights reserved.
 * 
 * @Package: com.smxy.recipe.domain 
 * @author: 雏实。   
 * Build File @date: 2018年7月4日 下午1:24:07 
 */
package com.smxy.recipe.entity;

/**
 * @author zpx
 *
 */
public class Material {
	private int f_id;
	private int f_rid;
	private Recipe recipe;
	private String f_name;
	public Material() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Material(int f_id, int f_rid, Recipe recipe, String f_name) {
		super();
		this.f_id = f_id;
		this.f_rid = f_rid;
		this.recipe = recipe;
		this.f_name = f_name;
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
	public String getF_name() {
		return f_name;
	}
	public void setF_name(String f_name) {
		this.f_name = f_name;
	}
	@Override
	public String toString() {
		return "Material [f_id=" + f_id + ", f_rid=" + f_rid + ", recipe=" + recipe + ", f_name=" + f_name + "]";
	}
}
