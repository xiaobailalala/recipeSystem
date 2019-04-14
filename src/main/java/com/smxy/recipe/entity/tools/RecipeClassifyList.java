/**
 * Copyright © 2018 eSunny Info. Developer Stu. All rights reserved.
 *
 * @Package:
 * @author: zpx
 * Build File @date: 2018/9/23 18:39
 * @Description TODO
 * @version 1.0
 */
package com.smxy.recipe.entity.tools;

import com.smxy.recipe.entity.Classify;
import com.smxy.recipe.entity.ClassifyOne;
import com.smxy.recipe.entity.ClassifyTwo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class RecipeClassifyList {
    private List<ClassifyOne> classifyOnes;
    private List<ClassifyTwo> classifyTwos;
    private List<Classify> classifies;
    private Integer selectedOne;
    private Integer selectedTwo;
    private Integer selectedThree;

}
