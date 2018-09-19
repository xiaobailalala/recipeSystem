/**
 * Copyright © 2018 eSunny Info. Developer Stu. All rights reserved.
 *
 * @Package:
 * @author: zpx
 * Build File @date: 2018/9/9 21:06
 * @Description TODO
 * @version 1.0
 */
package com.smxy.recipe.entity.SensorEntity;

public class Gp2y1051Data {

    private String pm;

    public Gp2y1051Data() {
    }

    public Gp2y1051Data(String pm) {
        this.pm = pm;
    }

    public String getPm() {
        return pm;
    }

    public void setPm(String pm) {
        this.pm = pm;
    }

    @Override
    public String toString() {
        return "Gp2y1051Data{" +
                "pm='" + pm + '\'' +
                '}';
    }
}
