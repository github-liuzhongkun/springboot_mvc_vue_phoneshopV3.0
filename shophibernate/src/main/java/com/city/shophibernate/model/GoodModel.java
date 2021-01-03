package com.city.shophibernate.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "good")
public class GoodModel {
    public GoodModel(){}
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "goodid")
    private int id = 0;
    @Column(name = "goodname")
    private String name = null;
    @Column(name = "price")
    private int price = 0;
    @Column(name = "qty")
    private int qty = 0;
    @Column(name = "city")
    private String city = null;
    @JsonIgnoreProperties("goods")
    @ManyToOne
    @JoinColumn(name = "categoryid")
    private CategoryModel category = null;
    @JsonIgnoreProperties("good")
    @OneToOne(mappedBy = "good")
    private GoodInfoModel goodInfo = null;
    @JsonIgnoreProperties("goods")
    @ManyToMany
    @JoinTable(
            name="goodarea",
            joinColumns={@JoinColumn (name="goodid",referencedColumnName="goodid")},
            inverseJoinColumns={@JoinColumn(name="aid",referencedColumnName="aid")}
    )
    private Set<AreaModel> areas = null;

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

    public Set<AreaModel> getAreas() {
        return areas;
    }

    public void setAreas(Set<AreaModel> areas) {
        this.areas = areas;
    }
}
