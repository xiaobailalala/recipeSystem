/**   
 * Copyright © 2018 eSunny Info. Developer Stu. All rights reserved.
 * 
 * @Package: com.smxy.recipe.domain 
 * @author: 雏实。   
 * Build File @date: 2018年7月4日 下午1:19:40 
 */
package com.smxy.recipe.entity;

import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author zpx
 *
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class Classify implements Serializable {
	private Integer fId;
	private String fName;
	private Integer fTid;
	private ClassifyTwo classifyTwo;
}
