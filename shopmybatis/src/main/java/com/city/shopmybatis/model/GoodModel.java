package com.city.shopmybatis.model;

import org.apache.ibatis.type.Alias;

import java.util.List;

@Alias("Good")
public class GoodModel {
    private int id = 0;
    private String name = null;

    private int price = 0;
    private int qty = 0;
    private String city = null;

    private CategoryModel category = null;

    private GoodInfoModel goodInfo = null;

    private List<AreaModel> areas = null;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public CategoryModel getCategory() {
        return category;
    }

    public void setCategory(CategoryModel category) {
        this.category = category;
    }

    public GoodInfoModel getGoodInfo() {
        return goodInfo;
    }

    public void setGoodInfo(GoodInfoModel goodInfo) {
        this.goodInfo = goodInfo;
    }

    public List<AreaModel> getAreas() {
        return areas;
    }

    public void setAreas(List<AreaModel> areas) {
        this.areas = areas;
    }
}
