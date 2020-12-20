package com.app.blogapplication.controller;

import com.app.blogapplication.entities.Tag;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TagController {

    @RequestMapping("/create-tag")
    public String createTag(Model model){
        model.addAttribute("tag", new Tag());
        return "tag/new-tag";
    }
}
