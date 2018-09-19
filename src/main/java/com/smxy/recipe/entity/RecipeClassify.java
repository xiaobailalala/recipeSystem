/**
 * Copyright © 2018 eSunny Info. Developer Stu. All rights reserved.
 *
 * @Package:
 * @author: zpx
 * Build File @date: 2018/9/17 19:59
 * @Description TODO
 * @version 1.0
 */
package com.smxy.recipe.entity;

public class RecipeClassify {
    private Integer fId;
    private Integer fRid;
    private Recipe recipe;
    private Integer fTwoId;
    private ClassifyTwo classifyTwo;
    private Integer fThreeId;
    private Classify classify;

    public RecipeClassify() {
    }

    public RecipeClassify(Integer fRid, Integer fTwoId, Integer fThreeId) {
        this.fRid = fRid;
        this.fTwoId = fTwoId;
        this.fThreeId = fThreeId;
    }

    public RecipeClassify(Integer fId, Integer fRid, Recipe recipe, Integer fTwoId, ClassifyTwo classifyTwo, Integer fThreeId, Classify classify) {
        this.fId = fId;
        this.fRid = fRid;
        this.recipe = recipe;
        this.fTwoId = fTwoId;
        this.classifyTwo = classifyTwo;
        this.fThreeId = fThreeId;
        this.classify = classify;
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

    public Integer getfTwoId() {
        return fTwoId;
    }

    public void setfTwoId(Integer fTwoId) {
        this.fTwoId = fTwoId;
    }

    public ClassifyTwo getClassifyTwo() {
        return classifyTwo;
    }

    public void setClassifyTwo(ClassifyTwo classifyTwo) {
        this.classifyTwo = classifyTwo;
    }

    public Integer getfThreeId() {
        return fThreeId;
    }

    public void setfThreeId(Integer fThreeId) {
        this.fThreeId = fThreeId;
    }

    public Classify getClassify() {
        return classify;
    }

    public void setClassify(Classify classify) {
        this.classify = classify;
    }

    @Override
    public String toString() {
        return "RecipeClassify{" +
                "fId=" + fId +
                ", fRid=" + fRid +
                ", recipe=" + recipe +
                ", fTwoId=" + fTwoId +
                ", classifyTwo=" + classifyTwo +
                ", fThreeId=" + fThreeId +
                ", classify=" + classify +
                '}';
    }
}
