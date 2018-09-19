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

public class RecipeTips {
    private Integer fId;
    private Integer fRid;
    private Recipe recipe;
    private Integer fTid;
    private Tips tips;

    public RecipeTips() {
    }

    public RecipeTips(Integer fRid, Integer fTid) {
        this.fRid = fRid;
        this.fTid = fTid;
    }

    public RecipeTips(Integer fId, Integer fRid, Recipe recipe, Integer fTid, Tips tips) {
        this.fId = fId;
        this.fRid = fRid;
        this.recipe = recipe;
        this.fTid = fTid;
        this.tips = tips;
    }

    public Integer getfId() {
        return fId;
    }

    public void setfId(Integer fId) {
        this.fId = fId;
    }

    public Integer getfRid() {
        return fRid;
    }

    public void setfRid(Integer fRid) {
        this.fRid = fRid;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public Integer getfTid() {
        return fTid;
    }

    public void setfTid(Integer fTid) {
        this.fTid = fTid;
    }

    public Tips getTips() {
        return tips;
    }

    public void setTips(Tips tips) {
        this.tips = tips;
    }

    @Override
    public String toString() {
        return "RecipeTips{" +
                "fId=" + fId +
                ", fRid=" + fRid +
                ", recipe=" + recipe +
                ", fTid=" + fTid +
                ", tips=" + tips +
                '}';
    }
}
