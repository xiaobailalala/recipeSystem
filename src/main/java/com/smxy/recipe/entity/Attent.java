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
public final class Attent {
	private Integer fId;
	private Integer fUidM;
	private Integer fUidO;
	private CommonUser commonUser;
	public Attent() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Attent(Integer fId, Integer fUidM, Integer fUidO, CommonUser commonUser) {
		this.fId = fId;
		this.fUidM = fUidM;
		this.fUidO = fUidO;
		this.commonUser = commonUser;
	}

	public Integer getfId() {
		return fId;
	}

	public void setfId(Integer fId) {
		this.fId = fId;
	}

	public Integer getfUidM() {
		return fUidM;
	}

	public void setfUidM(Integer fUidM) {
		this.fUidM = fUidM;
	}

	public Integer getfUidO() {
		return fUidO;
	}

	public void setfUidO(Integer fUidO) {
		this.fUidO = fUidO;
	}

	public CommonUser getCommonUser() {
		return commonUser;
	}

	public void setCommonUser(CommonUser commonUser) {
		this.commonUser = commonUser;
	}

	@Override
	public String toString() {
		return "Attent{" +
				"fId=" + fId +
				", fUidM=" + fUidM +
				", fUidO=" + fUidO +
				", commonUser=" + commonUser +
				'}';
	}
}
