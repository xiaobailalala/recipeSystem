package com.smxy.recipe.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * Demo MerchantProductFreight
 *
 * @author Yangyihui
 * @date 2018/11/22 0022 16:55
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class MerchantProductFreight implements Serializable {
    private Integer fId;
    private String fFreightname;
}
