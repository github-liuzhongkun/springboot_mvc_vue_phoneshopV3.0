package com.city.shopmybatis.service.impl;

import com.city.shopmybatis.dao.IUserDao;
import com.city.shopmybatis.model.UserModel;
import com.city.shopmybatis.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserDao iUserDao;
    @Override
    public UserModel login(UserModel user) throws Exception{
        return iUserDao.login(user);
    }
}
