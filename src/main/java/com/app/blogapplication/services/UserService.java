package com.app.blogapplication.services;

import com.app.blogapplication.dao.IUserRepository;
import com.app.blogapplication.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService{
    @Autowired
    private IUserRepository userRepository;


    @Override
    public String validateUser(String email, String password) {
        return "The user is authentic";
    }

    @Override
    public void registerUser(User user) {
        userRepository.save(user);
    }

    @Override
    public User getUser(int id) {
        return userRepository.getOne(id);
    }


}
