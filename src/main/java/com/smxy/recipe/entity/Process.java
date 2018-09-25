/**   
 * Copyright © 2018 eSunny Info. Developer Stu. All rights reserved.
 * 
 * @Package: com.smxy.recipe.domain 
 * @author: 雏实。   
 * Build File @date: 2018年7月4日 下午1:28:55 
 */
package com.smxy.recipe.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zpx
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public final class Process {
	private Integer fId;
	private Integer fRid;
	private Recipe recipe;
	private String fContent;
	private String fRequest;
	private String fCover;

	public Process(Integer fRid, String fContent, String fRequest, String fCover) {
		this.fRid = fRid;
		this.fContent = fContent;
		this.fRequest = fRequest;
		this.fCover = fCover;
	}
}
