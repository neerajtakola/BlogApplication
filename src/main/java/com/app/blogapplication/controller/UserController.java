package com.app.blogapplication.controller;

import com.app.blogapplication.entities.User;
import com.app.blogapplication.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class UserController {
    private final IUserService userService;

    @Autowired
    public UserController(IUserService userService){
        this.userService = userService;
    }

    @GetMapping("/login")
    public String showLoginForm(){
        return "user/login";
    }

    @PostMapping("/login")
    public void processLoginForm(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        response.setContentType("text/html");
        response.getWriter().write(userService.validateUser(email,password));
        response.setHeader("Refresh","2;/");
    }

    @GetMapping(value = "/register")
    public String showRegistrationForm(){
        return "user/register";
    }

    @PostMapping(value = "/register")
    public String processRegistrationForm(HttpServletRequest request){
        User user = new User();
        user.setName(request.getParameter("name"));
        user.setEmail(request.getParameter("email"));
        user.setPassword(request.getParameter("password"));
        userService.registerUser(user);
        return "home";
    }
}
