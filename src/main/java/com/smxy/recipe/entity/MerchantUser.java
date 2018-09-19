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
public final class MerchantUser {
	private Integer fId;
	private String fCover;
	private String fUsername;
	private String fAccount;
	private String fPassword;
	private String fProvince;
	private String fCity;
	private String fArea;
	private Integer fCount;
	public MerchantUser() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MerchantUser(Integer fId, String fCover, String fUsername, String fAccount, String fPassword, String fProvince, String fCity, String fArea, Integer fCount) {
		this.fId = fId;
		this.fCover = fCover;
		this.fUsername = fUsername;
		this.fAccount = fAccount;
		this.fPassword = fPassword;
		this.fProvince = fProvince;
		this.fCity = fCity;
		this.fArea = fArea;
		this.fCount = fCount;
	}

	public Integer getfId() {
		return fId;
	}

	public void setfId(Integer fId) {
		this.fId = fId;
	}

	public String getfCover() {
		return fCover;
	}

	public void setfCover(String fCover) {
		this.fCover = fCover;
	}

	public String getfUsername() {
		return fUsername;
	}

	public void setfUsername(String fUsername) {
		this.fUsername = fUsername;
	}

	public String getfAccount() {
		return fAccount;
	}

	public void setfAccount(String fAccount) {
		this.fAccount = fAccount;
	}

	public String getfPassword() {
		return fPassword;
	}

	public void setfPassword(String fPassword) {
		this.fPassword = fPassword;
	}

	public String getfProvince() {
		return fProvince;
	}

	public void setfProvince(String fProvince) {
		this.fProvince = fProvince;
	}

	public String getfCity() {
		return fCity;
	}

	public void setfCity(String fCity) {
		this.fCity = fCity;
	}

	public String getfArea() {
		return fArea;
	}

	public void setfArea(String fArea) {
		this.fArea = fArea;
	}

	public Integer getfCount() {
		return fCount;
	}

	public void setfCount(Integer fCount) {
		this.fCount = fCount;
	}

	@Override
	public String toString() {
		return "MerchantUser{" +
				"fId=" + fId +
				", fCover='" + fCover + '\'' +
				", fUsername='" + fUsername + '\'' +
				", fAccount='" + fAccount + '\'' +
				", fPassword='" + fPassword + '\'' +
				", fProvince='" + fProvince + '\'' +
				", fCity='" + fCity + '\'' +
				", fArea='" + fArea + '\'' +
				", fCount=" + fCount +
				'}';
	}
}
