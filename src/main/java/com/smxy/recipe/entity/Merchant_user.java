/**   
 * Copyright © 2018 eSunny Info. Developer Stu. All rights reserved.
 * 
 * @Package: com.smxy.recipe.domain 
 * @author: 雏实。   
 * Build File @date: 2018年7月4日 下午1:28:14 
 */
package com.smxy.recipe.entity;

/**
 * @author zpx
 *
 */
public class Merchant_user {
	private int f_id;
	private String f_cover;
	private String f_username;
	private String f_account;
	private String password;
	private String province;
	private String city;
	private String f_area;
	private int f_count;
	public Merchant_user() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Merchant_user(int f_id, String f_cover, String f_username, String f_account, String password,
			String province, String city, String f_area, int f_count) {
		super();
		this.f_id = f_id;
		this.f_cover = f_cover;
		this.f_username = f_username;
		this.f_account = f_account;
		this.password = password;
		this.province = province;
		this.city = city;
		this.f_area = f_area;
		this.f_count = f_count;
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
	public String getF_username() {
		return f_username;
	}
	public void setF_username(String f_username) {
		this.f_username = f_username;
	}
	public String getF_account() {
		return f_account;
	}
	public void setF_account(String f_account) {
		this.f_account = f_account;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getF_area() {
		return f_area;
	}
	public void setF_area(String f_area) {
		this.f_area = f_area;
	}
	public int getF_count() {
		return f_count;
	}
	public void setF_count(int f_count) {
		this.f_count = f_count;
	}
	@Override
	public String toString() {
		return "Merchant_user [f_id=" + f_id + ", f_cover=" + f_cover + ", f_username=" + f_username + ", f_account="
				+ f_account + ", password=" + password + ", province=" + province + ", city=" + city + ", f_area="
				+ f_area + ", f_count=" + f_count + "]";
	}
}
