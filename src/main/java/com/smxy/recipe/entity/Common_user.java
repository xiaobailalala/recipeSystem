/**   
 * Copyright © 2018 eSunny Info. Developer Stu. All rights reserved.
 * 
 * @Package: com.smxy.recipe.domain 
 * @author: 雏实。   
 * Build File @date: 2018年7月4日 下午1:22:24 
 */
package com.smxy.recipe.entity;

/**
 * @author zpx
 *
 */
public class Common_user {
	private int f_id;
	private String f_account;
	private String f_username;
	private String f_password;
	private String f_province="北京市";
	private String f_city="北京市";
	private String f_area="东城区";
	private String f_cover;
	private String f_sex="男";
	private String f_sign;
	private int f_pid=0;
	private Profession profession;
	public Common_user() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Common_user(int f_id, String f_account, String f_username, String f_password, String f_province,
			String f_city, String f_area, String f_cover, String f_sex, String f_sign, int f_pid,
			Profession profession) {
		super();
		this.f_id = f_id;
		this.f_account = f_account;
		this.f_username = f_username;
		this.f_password = f_password;
		this.f_province = f_province;
		this.f_city = f_city;
		this.f_area = f_area;
		this.f_cover = f_cover;
		this.f_sex = f_sex;
		this.f_sign = f_sign;
		this.f_pid = f_pid;
		this.profession = profession;
	}
	public int getF_id() {
		return f_id;
	}
	public void setF_id(int f_id) {
		this.f_id = f_id;
	}
	public String getF_account() {
		return f_account;
	}
	public void setF_account(String f_account) {
		this.f_account = f_account;
	}
	public String getF_username() {
		return f_username;
	}
	public void setF_username(String f_username) {
		this.f_username = f_username;
	}
	public String getF_password() {
		return f_password;
	}
	public void setF_password(String f_password) {
		this.f_password = f_password;
	}
	public String getF_province() {
		return f_province;
	}
	public void setF_province(String f_province) {
		this.f_province = f_province;
	}
	public String getF_city() {
		return f_city;
	}
	public void setF_city(String f_city) {
		this.f_city = f_city;
	}
	public String getF_area() {
		return f_area;
	}
	public void setF_area(String f_area) {
		this.f_area = f_area;
	}
	public String getF_cover() {
		return f_cover;
	}
	public void setF_cover(String f_cover) {
		this.f_cover = f_cover;
	}
	public String getF_sex() {
		return f_sex;
	}
	public void setF_sex(String f_sex) {
		this.f_sex = f_sex;
	}
	public String getF_sign() {
		return f_sign;
	}
	public void setF_sign(String f_sign) {
		this.f_sign = f_sign;
	}
	public int getF_pid() {
		return f_pid;
	}
	public void setF_pid(int f_pid) {
		this.f_pid = f_pid;
	}
	public Profession getProfession() {
		return profession;
	}
	public void setProfession(Profession profession) {
		this.profession = profession;
	}
	@Override
	public String toString() {
		return "Common_user [f_id=" + f_id + ", f_account=" + f_account + ", f_username=" + f_username + ", f_password="
				+ f_password + ", f_province=" + f_province + ", f_city=" + f_city + ", f_area=" + f_area + ", f_cover="
				+ f_cover + ", f_sex=" + f_sex + ", f_sign=" + f_sign + ", f_pid=" + f_pid + ", profession="
				+ profession + "]";
	}
}
