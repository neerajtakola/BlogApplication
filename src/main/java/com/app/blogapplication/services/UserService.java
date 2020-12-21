package com.app.blogapplication.services;

import com.app.blogapplication.entities.User;

public interface UserService {
    boolean validateUser(String email, String password);
    void registerUser(User user);
    User getUser(int id);
    User findUserByEmail(String email);
}
