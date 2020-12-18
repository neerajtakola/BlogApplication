package com.app.blogapplication.services;

import com.app.blogapplication.entities.User;

public interface IUserService {
    String validateUser(String email, String password);
    void registerUser(User user);
    User getUser(int id);
}
