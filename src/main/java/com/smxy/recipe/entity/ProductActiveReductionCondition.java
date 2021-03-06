package com.smxy.recipe.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * Demo ProductActiveReductionCondition
 *
 * @author Yangyihui
 * @date 2018/12/20 0020 21:28
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class ProductActiveReductionCondition implements Serializable {
    private Integer fId;
    private Integer fAid;
    private Double fFullMoney;
    private Double fReduceMoney;
}
