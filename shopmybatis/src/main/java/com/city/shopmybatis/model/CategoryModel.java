package com.city.shopmybatis.model;

import org.apache.ibatis.type.Alias;

import java.util.List;

@Alias("Category")
public class CategoryModel {
    private int id = 0;
    private String name = null;

    private List<GoodModel> goods = null;

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

    public List<GoodModel> getGoods() {
        return goods;
    }

    public void setGoods(List<GoodModel> goods) {
        this.goods = goods;
    }
}
