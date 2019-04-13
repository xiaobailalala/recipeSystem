/**   
 * Copyright © 2018 eSunny Info. Developer Stu. All rights reserved.
 * 
 * @Package: com.smxy.recipe.domain 
 * @author: 雏实。   
 * Build File @date: 2018年7月4日 下午1:24:07 
 */
package com.smxy.recipe.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author zpx
 *
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public final class Material {
	private Integer fId;
	private String fName;
	private String fCover;
}
