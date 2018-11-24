package com.smxy.recipe.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * Demo ClassifyProduct
 *
 * @author Yangyihui
 * @date 2018/11/18 0018 11:09
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ClassifyProduct implements Serializable {
    private Integer fId;
    private String fName;
}
