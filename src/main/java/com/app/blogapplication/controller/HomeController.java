package com.app.blogapplication.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

    public HomeController(){
        System.out.println("Home");
    }
    @RequestMapping(value = "/")
    public String showPosts(){
        return "redirect:/show-posts/show";
    }
}
