/**   
 * Copyright © 2018 eSunny Info. Developer Stu. All rights reserved.
 * 
 * @Package: com.smxy.recipe.domain 
 * @author: 雏实。   
 * Build File @date: 2018年7月4日 下午1:27:33 
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
public final class MerchantProduct {
	private Integer fId;
	private Integer fUid;
	private MerchantUser merchantUser;
	private String fName;
	private String fCover;
	private Integer fGood;
	private double fPrice;
	private String fCategory;
}
