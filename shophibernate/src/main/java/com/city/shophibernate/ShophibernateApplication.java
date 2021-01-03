package com.city.shophibernate;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.persistence.EntityManagerFactory;

@SpringBootApplication
public class ShophibernateApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShophibernateApplication.class, args);
    }


}
