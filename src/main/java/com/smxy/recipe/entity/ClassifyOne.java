/**
 * Copyright © 2018 eSunny Info. Developer Stu. All rights reserved.
 *
 * @Package:
 * @author: zpx
 * Build File @date: 2018/8/23 21:55
 * @Description TODO
 * @version 1.0
 */
package com.smxy.recipe.entity;

import java.io.Serializable;
import java.util.List;

public class ClassifyOne implements Serializable {
    private Integer fId;
    private String fName;
    private List<ClassifyTwo> classifyTwos;

    public ClassifyOne() {
    }

    public ClassifyOne(Integer fId, String fName, List<ClassifyTwo> classifyTwos) {
        this.fId = fId;
        this.fName = fName;
        this.classifyTwos = classifyTwos;
    }

    public Integer getfId() {
        return fId;
    }

    public void setfId(Integer fId) {
        this.fId = fId;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public List<ClassifyTwo> getClassifyTwos() {
        return classifyTwos;
    }

    public void setClassifyTwos(List<ClassifyTwo> classifyTwos) {
        this.classifyTwos = classifyTwos;
    }

    @Override
    public String toString() {
        return "ClassifyOne{" +
                "fId=" + fId +
                ", fName='" + fName + '\'' +
                ", classifyTwos=" + classifyTwos +
                '}';
    }
}
