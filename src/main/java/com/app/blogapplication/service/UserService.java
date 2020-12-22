package com.app.blogapplication.service;

import com.app.blogapplication.entity.User;

public interface UserService {
    boolean validateUser(String email, String password);
    void registerUser(User user);
    User getUser(int id);
    User findUserByEmail(String email);
}
