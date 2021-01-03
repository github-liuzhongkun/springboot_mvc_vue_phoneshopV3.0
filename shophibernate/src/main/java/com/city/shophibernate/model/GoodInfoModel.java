package com.city.shophibernate.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "goodinfo")
public class GoodInfoModel {
    public GoodInfoModel(){}
    @Id
    @Column(name = "goodid")
    @GenericGenerator(name = "GEN",strategy = "foreign",parameters = {@Parameter(name="property",value="good")})
    @GeneratedValue(generator = "GEN")
    private int id = 0;
    @Lob
    @Column(name = "photo")
    @JsonIgnore
    private byte[] photo = null;
    @Column(name = "filename")
    private String filename = null;
    @Column(name = "contentType")
    private String contentType = null;
    @JsonIgnoreProperties("goodInfo")
    @OneToOne
    @PrimaryKeyJoinColumn
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
