/**
 * Copyright © 2018 eSunny Info. Developer Stu. All rights reserved.
 *
 * @Package:
 * @author: zpx
 * Build File @date: 2018/8/23 21:56
 * @Description TODO
 * @version 1.0
 */
package com.smxy.recipe.entity;

import java.io.Serializable;
import java.util.List;

public class ClassifyTwo implements Serializable {
    private Integer fId;
    private String fName;
    private Integer fOid;
    private String fCover;
    private String fBg;
    private String fColor;
    private ClassifyOne classifyOne;
    private List<Classify> classifies;

    public ClassifyTwo() {
    }

    public ClassifyTwo(Integer fId, String fName, Integer fOid, String fCover, String fBg, String fColor, ClassifyOne classifyOne, List<Classify> classifies) {
        this.fId = fId;
        this.fName = fName;
        this.fOid = fOid;
        this.fCover = fCover;
        this.fBg = fBg;
        this.fColor = fColor;
        this.classifyOne = classifyOne;
        this.classifies = classifies;
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

    public Integer getfOid() {
        return fOid;
    }

    public void setfOid(Integer fOid) {
        this.fOid = fOid;
    }

    public String getfCover() {
        return fCover;
    }

    public void setfCover(String fCover) {
        this.fCover = fCover;
    }

    public String getfBg() {
        return fBg;
    }

    public void setfBg(String fBg) {
        this.fBg = fBg;
    }

    public String getfColor() {
        return fColor;
    }

    public void setfColor(String fColor) {
        this.fColor = fColor;
    }

    public ClassifyOne getClassifyOne() {
        return classifyOne;
    }

    public void setClassifyOne(ClassifyOne classifyOne) {
        this.classifyOne = classifyOne;
    }

    public List<Classify> getClassifies() {
        return classifies;
    }

    public void setClassifies(List<Classify> classifies) {
        this.classifies = classifies;
    }

    @Override
    public String toString() {
        return "ClassifyTwo{" +
                "fId=" + fId +
                ", fName='" + fName + '\'' +
                ", fOid=" + fOid +
                ", fCover='" + fCover + '\'' +
                ", fBg='" + fBg + '\'' +
                ", fColor='" + fColor + '\'' +
                ", classifyOne=" + classifyOne +
                ", classifies=" + classifies +
                '}';
    }
}
