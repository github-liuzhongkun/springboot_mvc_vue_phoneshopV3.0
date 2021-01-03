package com.city.shophibernate.service.impl;

import com.city.shophibernate.model.UserModel;
import com.city.shophibernate.service.IUserService;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserServiceImpl implements IUserService {

    @Autowired
    private SessionFactory sessionFactory=null;
    @Override
    public UserModel login(UserModel user) throws Exception{
        return sessionFactory.getCurrentSession().createQuery("select um from UserModel um where um.username=:username and um.password=:password",UserModel.class)
                .setParameter("username",user.getUsername())
                .setParameter("password",user.getPassword())
                .getSingleResult();
    }
}
