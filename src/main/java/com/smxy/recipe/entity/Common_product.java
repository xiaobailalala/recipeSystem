/**   
 * Copyright © 2018 eSunny Info. Developer Stu. All rights reserved.
 * 
 * @Package: com.smxy.recipe.domain 
 * @author: 雏实。   
 * Build File @date: 2018年7月4日 下午1:21:36 
 */
package com.smxy.recipe.entity;

/**
 * @author zpx
 *
 */
public class Common_product {
	private int f_id;
	private String f_cover;
	private int f_uid;
	private Common_user common_user;
	public Common_product() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Common_product(int f_id, String f_cover, int f_uid, Common_user common_user) {
		super();
		this.f_id = f_id;
		this.f_cover = f_cover;
		this.f_uid = f_uid;
		this.common_user = common_user;
	}
	public int getF_id() {
		return f_id;
	}
	public void setF_id(int f_id) {
		this.f_id = f_id;
	}
	public String getF_cover() {
		return f_cover;
	}
	public void setF_cover(String f_cover) {
		this.f_cover = f_cover;
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
	@Override
	public String toString() {
		return "Common_product [f_id=" + f_id + ", f_cover=" + f_cover + ", f_uid=" + f_uid + ", common_user="
				+ common_user + "]";
	}
}
