/**
 * Copyright © 2018 eSunny Info. Developer Stu. All rights reserved.
 *
 * @Package:
 * @author: zpx
 * Build File @date: 2018/8/23 21:56
 * @Description TODO
 * @version 1.0
 */
package com.smxy.recipe.entity;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ClassifyTwo implements Serializable {
    private Integer fId;
    private String fName;
    private Integer fOid;
    private String fCover;
    private String fBg;
    private String fColor;
    private ClassifyOne classifyOne;
    private List<Classify> classifies;
}
