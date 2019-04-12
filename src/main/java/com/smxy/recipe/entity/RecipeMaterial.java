/**
 * Copyright © 2018 eSunny Info. Developer Stu. All rights reserved.
 *
 * @Package:
 * @author: zpx
 * Build File @date: 2018/9/18 16:30
 * @Description TODO
 * @version 1.0
 */
package com.smxy.recipe.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecipeMaterial {
    private Integer fId;
    private Integer fRid;
    private Recipe recipe;
    private Integer fMid;
    private Material material;
    private String fNumber;
    private String fName;

    public RecipeMaterial(Integer fRid, Integer fMid, String fNumber, String fName) {
        this.fRid = fRid;
        this.fMid = fMid;
        this.fNumber = fNumber;
        this.fName = fName;
    }

}
