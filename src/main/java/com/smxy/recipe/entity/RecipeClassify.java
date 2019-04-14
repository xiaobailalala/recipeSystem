/**
 * Copyright © 2018 eSunny Info. Developer Stu. All rights reserved.
 *
 * @Package:
 * @author: zpx
 * Build File @date: 2018/9/17 19:59
 * @Description TODO
 * @version 1.0
 */
package com.smxy.recipe.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class RecipeClassify {
    private Integer fId;
    private Integer fRid;
    private Recipe recipe;
    private Integer fTwoId;
    private ClassifyTwo classifyTwo;
    private Integer fThreeId;
    private Classify classify;

    public RecipeClassify(Integer fRid, Integer fTwoId, Integer fThreeId) {
        this.fRid = fRid;
        this.fTwoId = fTwoId;
        this.fThreeId = fThreeId;
    }
}
