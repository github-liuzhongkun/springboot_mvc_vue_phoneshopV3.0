package com.city.shophibernate.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "category")
public class CategoryModel {
    public CategoryModel(){}
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "categoryid")
    private int id = 0;
    @Column(name = "categoryname")
    private String name = null;
    @JsonIgnoreProperties("category")
    @OneToMany(mappedBy = "category")
    private Set<GoodModel> goods = null;

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

    public Set<GoodModel> getGoods() {
        return goods;
    }

    public void setGoods(Set<GoodModel> goods) {
        this.goods = goods;
    }
}
