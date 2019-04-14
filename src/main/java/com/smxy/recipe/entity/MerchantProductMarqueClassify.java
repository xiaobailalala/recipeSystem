package com.smxy.recipe.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * Demo MerchantProductMarqueClassify
 *
 * @author Yangyihui
 * @date 2018/11/19 0019 11:09
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class MerchantProductMarqueClassify implements Serializable {
    private Integer fId;
    private String fMarquename;
}
