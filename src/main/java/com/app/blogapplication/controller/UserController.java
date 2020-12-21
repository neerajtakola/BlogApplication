package com.app.blogapplication.controller;

import com.app.blogapplication.entities.User;
import com.app.blogapplication.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/login")
    public String showLoginForm(){
        return "user/login";
    }

    @PostMapping("/login")
    public String processLoginForm(@RequestParam String email,@RequestParam String password){
        if(userService.validateUser(email,password)){
            return "redirect:/user";
        }
        return "redirect:/";
    }

    @GetMapping(value = "/register")
    public String showRegistrationForm(Model model){
        model.addAttribute("user",new User());
        return "user/register";
    }

    @PostMapping(value = "/register")
    public String processRegistrationForm(@ModelAttribute User user){
        userService.registerUser(user);
        return "redirect:/";
    }
}
