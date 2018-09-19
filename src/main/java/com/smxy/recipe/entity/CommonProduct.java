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
public final class CommonProduct {
	private Integer fId;
	private String fCover;
	private Integer fUid;
	private CommonUser commonUser;
	public CommonProduct() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CommonProduct(Integer fId, String fCover, Integer fUid, CommonUser commonUser) {
		this.fId = fId;
		this.fCover = fCover;
		this.fUid = fUid;
		this.commonUser = commonUser;
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

	@Override
	public String toString() {
		return "CommonProduct{" +
				"fId=" + fId +
				", fCover='" + fCover + '\'' +
				", fUid=" + fUid +
				", commonUser=" + commonUser +
				'}';
	}
}
