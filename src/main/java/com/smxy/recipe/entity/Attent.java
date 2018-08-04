/**   
 * Copyright © 2018 eSunny Info. Developer Stu. All rights reserved.
 * 
 * @Package: com.smxy.recipe.domain 
 * @author: 雏实。   
 * Build File @date: 2018年7月4日 下午1:19:06 
 */
package com.smxy.recipe.entity;

/**
 * @author zpx
 *
 */
public class Attent {
	private int f_id;
	private int f_uid_m;
	private int f_uid_o;
	private Common_user common_user;
	public Attent() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Attent(int f_id, int f_uid_m, int f_uid_o, Common_user common_user) {
		super();
		this.f_id = f_id;
		this.f_uid_m = f_uid_m;
		this.f_uid_o = f_uid_o;
		this.common_user = common_user;
	}
	public int getF_id() {
		return f_id;
	}
	public void setF_id(int f_id) {
		this.f_id = f_id;
	}
	public int getF_uid_m() {
		return f_uid_m;
	}
	public void setF_uid_m(int f_uid_m) {
		this.f_uid_m = f_uid_m;
	}
	public int getF_uid_o() {
		return f_uid_o;
	}
	public void setF_uid_o(int f_uid_o) {
		this.f_uid_o = f_uid_o;
	}
	public Common_user getCommon_user() {
		return common_user;
	}
	public void setCommon_user(Common_user common_user) {
		this.common_user = common_user;
	}
	@Override
	public String toString() {
		return "Common_attent [f_id=" + f_id + ", f_uid_m=" + f_uid_m + ", f_uid_o=" + f_uid_o + ", common_user="
				+ common_user + "]";
	}
}
