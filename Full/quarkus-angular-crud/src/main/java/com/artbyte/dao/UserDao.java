package com.artbyte.dao;

import javax.enterprise.context.ApplicationScoped;

import com.artbyte.model.User;

import io.quarkus.mongodb.panache.PanacheMongoRepository;

@ApplicationScoped
public class UserDao implements PanacheMongoRepository<User>{
    
}
