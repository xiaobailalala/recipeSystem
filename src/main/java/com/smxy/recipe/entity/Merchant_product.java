/**   
 * Copyright © 2018 eSunny Info. Developer Stu. All rights reserved.
 * 
 * @Package: com.smxy.recipe.domain 
 * @author: 雏实。   
 * Build File @date: 2018年7月4日 下午1:27:33 
 */
package com.smxy.recipe.entity;

/**
 * @author zpx
 *
 */
public class Merchant_product {
	private int f_id;
	private int f_uid;
	private Merchant_user merchant_user;
	private String f_name;
	private String f_cover;
	private int f_good;
	private double f_price;
	public Merchant_product() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Merchant_product(int f_id, int f_uid, Merchant_user merchant_user, String f_name, String f_cover, int f_good,
			double f_price) {
		super();
		this.f_id = f_id;
		this.f_uid = f_uid;
		this.merchant_user = merchant_user;
		this.f_name = f_name;
		this.f_cover = f_cover;
		this.f_good = f_good;
		this.f_price = f_price;
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
	public Merchant_user getMerchant_user() {
		return merchant_user;
	}
	public void setMerchant_user(Merchant_user merchant_user) {
		this.merchant_user = merchant_user;
	}
	public String getF_name() {
		return f_name;
	}
	public void setF_name(String f_name) {
		this.f_name = f_name;
	}
	public String getF_cover() {
		return f_cover;
	}
	public void setF_cover(String f_cover) {
		this.f_cover = f_cover;
	}
	public int getF_good() {
		return f_good;
	}
	public void setF_good(int f_good) {
		this.f_good = f_good;
	}
	public double getF_price() {
		return f_price;
	}
	public void setF_price(double f_price) {
		this.f_price = f_price;
	}
	@Override
	public String toString() {
		return "Merchant_product [f_id=" + f_id + ", f_uid=" + f_uid + ", merchant_user=" + merchant_user + ", f_name="
				+ f_name + ", f_cover=" + f_cover + ", f_good=" + f_good + ", f_price=" + f_price + "]";
	}
}
