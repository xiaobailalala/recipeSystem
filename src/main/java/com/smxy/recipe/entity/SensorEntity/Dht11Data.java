/**
 * Copyright © 2018 eSunny Info. Developer Stu. All rights reserved.
 *
 * @Package:
 * @author: zpx
 * Build File @date: 2018/9/3 12:24
 * @Description TODO
 * @version 1.0
 */
package com.smxy.recipe.entity.SensorEntity;

public class Dht11Data {
    private String rh;
    private String tmp;

    public Dht11Data() {
    }

    public Dht11Data(String rh, String tmp) {
        this.rh = rh;
        this.tmp = tmp;
    }

    public String getRh() {
        return rh;
    }

    public void setRh(String rh) {
        this.rh = rh;
    }

    public String getTmp() {
        return tmp;
    }

    public void setTmp(String tmp) {
        this.tmp = tmp;
    }

    @Override
    public String toString() {
        return "Dht11{" +
                "rh='" + rh + '\'' +
                ", tmp='" + tmp + '\'' +
                '}';
    }
}
