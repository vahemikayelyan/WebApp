package com.springapp.mvc.dao;

import com.springapp.mvc.model.User;

import java.util.List;


public interface UserDao {

    User findById(int id);

    User findBySSO(String sso);

    void save(User user);

    void deleteBySSO(String sso);

    List<User> findAllUsers();
}