package com.example.huei_lian.hotelinformation;

import android.graphics.Bitmap;

/**
 * Created by Huei-Lian on 2017/6/7.
 */

public class Hotel {
    private String name;
    private String address;
    private Bitmap imgUrl;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Bitmap getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(Bitmap imgUrl) {
        this.imgUrl = imgUrl;
    }
}