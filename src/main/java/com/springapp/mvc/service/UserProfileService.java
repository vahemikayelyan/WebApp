package com.springapp.mvc.service;

import java.util.List;

import com.springapp.mvc.model.UserProfile;

public interface UserProfileService {

    UserProfile findById(int id);

    UserProfile findByType(String type);

    List<UserProfile> findAll();
}