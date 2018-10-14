/**
 * Copyright © 2018 eSunny Info. Developer Stu. All rights reserved.
 *
 * @Package:
 * @author: zpx
 * Build File @date: 2018/9/18 20:12
 * @Description TODO
 * @version 1.0
 */
package com.smxy.recipe.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecipeTips {
    private Integer fId;
    private Integer fRid;
    private Recipe recipe;
    private Integer fTid;
    private Tips tips;

    public RecipeTips(Integer fRid, Integer fTid) {
        this.fRid = fRid;
        this.fTid = fTid;
    }

}
