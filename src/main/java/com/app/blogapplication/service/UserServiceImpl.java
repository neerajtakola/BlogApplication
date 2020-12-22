package com.app.blogapplication.service;

import com.app.blogapplication.repository.UserRepository;
import com.app.blogapplication.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;


    @Override
    public boolean validateUser(String email, String password) {
        return true;
    }

    @Override
    public void registerUser(User user) {
        userRepository.save(user);
    }

    @Override
    public User getUser(int id) {
        return userRepository.getOne(id);
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmailLike(email);
    }
}
