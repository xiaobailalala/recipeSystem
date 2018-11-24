package com.smxy.recipe.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * Demo MerchantProductClassify
 *
 * @author Yangyihui
 * @date 2018/11/19 0019 11:03
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MerchantProductClassify implements Serializable {
    private Integer fId;
    private String fName;
}
