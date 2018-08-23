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
public class CommonUser {
	private Integer fId;
	private String fAccount;
	private String fUsername;
	private String fPassword;
	private String fProvince="北京市";
	private String fCity="北京市";
	private String fArea="东城区";
	private String fCover;
	private String fSex="男";
	private String fSign;
	private Integer fPid=0;
	private Profession profession;
	public CommonUser() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CommonUser(Integer fId, String fAccount, String fUsername, String fPassword, String fProvince, String fCity, String fArea, String fCover, String fSex, String fSign, Integer fPid, Profession profession) {
		this.fId = fId;
		this.fAccount = fAccount;
		this.fUsername = fUsername;
		this.fPassword = fPassword;
		this.fProvince = fProvince;
		this.fCity = fCity;
		this.fArea = fArea;
		this.fCover = fCover;
		this.fSex = fSex;
		this.fSign = fSign;
		this.fPid = fPid;
		this.profession = profession;
	}

	public Integer getfId() {
		return fId;
	}

	public void setfId(Integer fId) {
		this.fId = fId;
	}

	public String getfAccount() {
		return fAccount;
	}

	public void setfAccount(String fAccount) {
		this.fAccount = fAccount;
	}

	public String getfUsername() {
		return fUsername;
	}

	public void setfUsername(String fUsername) {
		this.fUsername = fUsername;
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

	public String getfCover() {
		return fCover;
	}

	public void setfCover(String fCover) {
		this.fCover = fCover;
	}

	public String getfSex() {
		return fSex;
	}

	public void setfSex(String fSex) {
		this.fSex = fSex;
	}

	public String getfSign() {
		return fSign;
	}

	public void setfSign(String fSign) {
		this.fSign = fSign;
	}

	public Integer getfPid() {
		return fPid;
	}

	public void setfPid(Integer fPid) {
		this.fPid = fPid;
	}

	public Profession getProfession() {
		return profession;
	}

	public void setProfession(Profession profession) {
		this.profession = profession;
	}

	@Override
	public String toString() {
		return "CommonUser{" +
				"fId=" + fId +
				", fAccount='" + fAccount + '\'' +
				", fUsername='" + fUsername + '\'' +
				", fPassword='" + fPassword + '\'' +
				", fProvince='" + fProvince + '\'' +
				", fCity='" + fCity + '\'' +
				", fArea='" + fArea + '\'' +
				", fCover='" + fCover + '\'' +
				", fSex='" + fSex + '\'' +
				", fSign='" + fSign + '\'' +
				", fPid=" + fPid +
				", profession=" + profession +
				'}';
	}
}
