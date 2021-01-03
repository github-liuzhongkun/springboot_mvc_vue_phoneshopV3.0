package com.city.shopmybatis.dao;

import com.city.shopmybatis.model.UserModel;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IUserDao {
    public UserModel login(UserModel user) throws Exception;
}
