/**   
 * Copyright © 2018 eSunny Info. Developer Stu. All rights reserved.
 * 
 * @Package: com.smxy.recipe.domain 
 * @author: 雏实。   
 * Build File @date: 2018年7月4日 下午1:24:59 
 */
package com.smxy.recipe.entity;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author zpx
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public final class MerchantComment {
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
}
