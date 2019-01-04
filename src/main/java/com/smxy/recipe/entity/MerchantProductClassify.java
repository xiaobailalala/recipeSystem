package com.smxy.recipe.entity;

import lombok.*;

import java.io.Serializable;

/**
 * Demo MerchantProductClassify
 *
 * @author Yangyihui
 * @date 2018/11/19 0019 11:03
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MerchantProductClassify implements Serializable {
    private Integer fId;
    private String fName;
    private String fCover;
}
