package com.city.shopmybatis.model;

import org.apache.ibatis.type.Alias;

@Alias("GoodInfo")
public class GoodInfoModel {
    private int id = 0;
    private byte[] photo = null;
    private String filename = null;
    private String contentType = null;

    private GoodModel good = null;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public GoodModel getGood() {
        return good;
    }

    public void setGood(GoodModel good) {
        this.good = good;
    }
}
