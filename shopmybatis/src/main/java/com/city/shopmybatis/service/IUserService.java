package com.city.shopmybatis.service;

import com.city.shopmybatis.model.UserModel;

public interface IUserService {
    public UserModel login(UserModel user) throws Exception;
}
