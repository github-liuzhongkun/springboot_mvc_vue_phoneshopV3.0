package com.city.shopmybatis.model;

import org.apache.ibatis.type.Alias;

@Alias("User")
public class UserModel {
    private int id = 0;
    private String username = null;
    private String password = null;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

