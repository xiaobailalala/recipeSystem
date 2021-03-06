/**   
 * Copyright © 2018 eSunny Info. Developer Stu. All rights reserved.
 * 
 * @Package: com.smxy.recipe.domain 
 * @author: 雏实。   
 * Build File @date: 2018年7月4日 下午1:23:06 
 */
package com.smxy.recipe.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author zpx
 *
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class FoodComment {
	private Integer fId;
	private Integer fRid;
	private Recipe recipe;
	private Integer fUid;
	private CommonUser commonUser;
	private String fContent;
	private String fRelease;
	private Integer fGood;
	private String fCover;
	private Integer fReplyid;
	private FoodComment foodComment;
	private Integer userGreat;
	private List<FoodCommentGreat> foodCommentGreats;
}
