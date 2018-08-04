/**   
 * Copyright © 2018 eSunny Info. Developer Stu. All rights reserved.
 * 
 * @Package: com.smxy.recipe.domain 
 * @author: 雏实。   
 * Build File @date: 2018年7月4日 下午1:24:59 
 */
package com.smxy.recipe.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author zpx
 *
 */
public class Merchant_comment {
	private int f_id;
	private int f_pid;
	private Merchant_product merchant_product;
	private int f_uid;
	private Common_user common_user;
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date f_release;
	private String f_content;
	private int f_good;
	private String f_cover;
	public Merchant_comment() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Merchant_comment(int f_id, int f_pid, Merchant_product merchant_product, int f_uid, Common_user common_user,
			Date f_release, String f_content, int f_good, String f_cover) {
		super();
		this.f_id = f_id;
		this.f_pid = f_pid;
		this.merchant_product = merchant_product;
		this.f_uid = f_uid;
		this.common_user = common_user;
		this.f_release = f_release;
		this.f_content = f_content;
		this.f_good = f_good;
		this.f_cover = f_cover;
	}
	public int getF_id() {
		return f_id;
	}
	public void setF_id(int f_id) {
		this.f_id = f_id;
	}
	public int getF_pid() {
		return f_pid;
	}
	public void setF_pid(int f_pid) {
		this.f_pid = f_pid;
	}
	public Merchant_product getMerchant_product() {
		return merchant_product;
	}
	public void setMerchant_product(Merchant_product merchant_product) {
		this.merchant_product = merchant_product;
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
	public Date getF_release() {
		return f_release;
	}
	public void setF_release(Date f_release) {
		this.f_release = f_release;
	}
	public String getF_content() {
		return f_content;
	}
	public void setF_content(String f_content) {
		this.f_content = f_content;
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
		return "Merchant_comment [f_id=" + f_id + ", f_pid=" + f_pid + ", merchant_product=" + merchant_product
				+ ", f_uid=" + f_uid + ", common_user=" + common_user + ", f_release=" + f_release + ", f_content="
				+ f_content + ", f_good=" + f_good + ", f_cover=" + f_cover + "]";
	}
}
