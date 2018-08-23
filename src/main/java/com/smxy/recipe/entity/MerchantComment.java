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
public class MerchantComment {
	private Integer fId;
	private Integer fPid;
	private MerchantProduct merchantProduct;
	private Integer fUid;
	private CommonUser commonUser;
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date fRelease;
	private String fContent;
	private Integer fGood;
	private String fCover;
	public MerchantComment() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MerchantComment(Integer fId, Integer fPid, MerchantProduct merchantProduct, Integer fUid, CommonUser commonUser, Date fRelease, String fContent, Integer fGood, String fCover) {
		this.fId = fId;
		this.fPid = fPid;
		this.merchantProduct = merchantProduct;
		this.fUid = fUid;
		this.commonUser = commonUser;
		this.fRelease = fRelease;
		this.fContent = fContent;
		this.fGood = fGood;
		this.fCover = fCover;
	}

	public Integer getfId() {
		return fId;
	}

	public void setfId(Integer fId) {
		this.fId = fId;
	}

	public Integer getfPid() {
		return fPid;
	}

	public void setfPid(Integer fPid) {
		this.fPid = fPid;
	}

	public MerchantProduct getMerchantProduct() {
		return merchantProduct;
	}

	public void setMerchantProduct(MerchantProduct merchantProduct) {
		this.merchantProduct = merchantProduct;
	}

	public Integer getfUid() {
		return fUid;
	}

	public void setfUid(Integer fUid) {
		this.fUid = fUid;
	}

	public CommonUser getCommonUser() {
		return commonUser;
	}

	public void setCommonUser(CommonUser commonUser) {
		this.commonUser = commonUser;
	}

	public Date getfRelease() {
		return fRelease;
	}

	public void setfRelease(Date fRelease) {
		this.fRelease = fRelease;
	}

	public String getfContent() {
		return fContent;
	}

	public void setfContent(String fContent) {
		this.fContent = fContent;
	}

	public Integer getfGood() {
		return fGood;
	}

	public void setfGood(Integer fGood) {
		this.fGood = fGood;
	}

	public String getfCover() {
		return fCover;
	}

	public void setfCover(String fCover) {
		this.fCover = fCover;
	}

	@Override
	public String toString() {
		return "MerchantComment{" +
				"fId=" + fId +
				", fPid=" + fPid +
				", merchantProduct=" + merchantProduct +
				", fUid=" + fUid +
				", commonUser=" + commonUser +
				", fRelease=" + fRelease +
				", fContent='" + fContent + '\'' +
				", fGood=" + fGood +
				", fCover='" + fCover + '\'' +
				'}';
	}
}
