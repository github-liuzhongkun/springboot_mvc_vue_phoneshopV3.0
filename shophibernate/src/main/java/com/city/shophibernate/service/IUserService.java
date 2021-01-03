package com.city.shophibernate.service;

import com.city.shophibernate.model.UserModel;

public interface IUserService {
    public UserModel login(UserModel user) throws Exception;
}
