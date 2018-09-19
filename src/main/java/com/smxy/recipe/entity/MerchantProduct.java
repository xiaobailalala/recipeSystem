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
public final class MerchantProduct {
	private Integer fId;
	private Integer fUid;
	private MerchantUser merchantUser;
	private String fName;
	private String fCover;
	private Integer fGood;
	private double fPrice;
	public MerchantProduct() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MerchantProduct(Integer fId, Integer fUid, MerchantUser merchantUser, String fName, String fCover, Integer fGood, double fPrice) {
		this.fId = fId;
		this.fUid = fUid;
		this.merchantUser = merchantUser;
		this.fName = fName;
		this.fCover = fCover;
		this.fGood = fGood;
		this.fPrice = fPrice;
	}

	public Integer getfId() {
		return fId;
	}

	public void setfId(Integer fId) {
		this.fId = fId;
	}

	public Integer getfUid() {
		return fUid;
	}

	public void setfUid(Integer fUid) {
		this.fUid = fUid;
	}

	public MerchantUser getMerchantUser() {
		return merchantUser;
	}

	public void setMerchantUser(MerchantUser merchantUser) {
		this.merchantUser = merchantUser;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getfCover() {
		return fCover;
	}

	public void setfCover(String fCover) {
		this.fCover = fCover;
	}

	public Integer getfGood() {
		return fGood;
	}

	public void setfGood(Integer fGood) {
		this.fGood = fGood;
	}

	public double getfPrice() {
		return fPrice;
	}

	public void setfPrice(double fPrice) {
		this.fPrice = fPrice;
	}

	@Override
	public String toString() {
		return "MerchantProduct{" +
				"fId=" + fId +
				", fUid=" + fUid +
				", merchantUser=" + merchantUser +
				", fName='" + fName + '\'' +
				", fCover='" + fCover + '\'' +
				", fGood=" + fGood +
				", fPrice=" + fPrice +
				'}';
	}
}
