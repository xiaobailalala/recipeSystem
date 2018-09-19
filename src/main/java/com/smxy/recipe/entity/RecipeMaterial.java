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

public class RecipeMaterial {
    private Integer fId;
    private Integer fRid;
    private Recipe recipe;
    private Integer fMid;
    private Material material;
    private String fNumber;

    public RecipeMaterial() {
    }

    public RecipeMaterial(Integer fRid, Integer fMid, String fNumber) {
        this.fRid = fRid;
        this.fMid = fMid;
        this.fNumber = fNumber;
    }

    public RecipeMaterial(Integer fId, Integer fRid, Recipe recipe, Integer fMid, Material material, String fNumber) {
        this.fId = fId;
        this.fRid = fRid;
        this.recipe = recipe;
        this.fMid = fMid;
        this.material = material;
        this.fNumber = fNumber;
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

    public Integer getfMid() {
        return fMid;
    }

    public void setfMid(Integer fMid) {
        this.fMid = fMid;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public String getfNumber() {
        return fNumber;
    }

    public void setfNumber(String fNumber) {
        this.fNumber = fNumber;
    }

    @Override
    public String toString() {
        return "RecipeMaterial{" +
                "fId=" + fId +
                ", fRid=" + fRid +
                ", recipe=" + recipe +
                ", fMid=" + fMid +
                ", material=" + material +
                ", fNumber='" + fNumber + '\'' +
                '}';
    }
}
