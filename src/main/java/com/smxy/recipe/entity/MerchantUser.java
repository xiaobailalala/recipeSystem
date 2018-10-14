/**   
 * Copyright © 2018 eSunny Info. Developer Stu. All rights reserved.
 * 
 * @Package: com.smxy.recipe.domain 
 * @author: 雏实。   
 * Build File @date: 2018年7月4日 下午1:28:14 
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
public final class MerchantUser {
	private Integer fId;
	private String fCover;
	private String fUsername;
	private String fAccount;
	private String fPassword;
	private String fBirth;
	private String fProvince;
	private String fCity;
	private String fArea;
	private Integer fCount;
}
