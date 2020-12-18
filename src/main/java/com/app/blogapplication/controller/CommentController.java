package com.app.blogapplication.controller;

import com.app.blogapplication.entities.Comment;
import com.app.blogapplication.services.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class CommentController {
    @Autowired
    private ICommentService commentService;

    @RequestMapping(value = "/comment/{id}")
    public String showComments(@PathVariable int id, Model model) {
        model.addAttribute("comments", commentService.getComments(id));
        return "comment/show-comments";
    }

    @GetMapping(value="/add-comment/{id}")
    public String showCommentForm(@PathVariable int id){
       return "comment/add-comment";
    }

    @PostMapping(value ="/add-comment")
    public String addComment(@RequestParam String name,@RequestParam String email, @RequestParam String formComment){
        Comment comment = new Comment();
        comment.setComment(formComment);
        commentService.addComment(comment);
        return "redirect:/";
    }



    @RequestMapping(value="/comment-update/{id}")
    public String updateComment(@PathVariable int id, Model model){
        Comment comment = commentService.showPostById(id);
        model.addAttribute("comment",comment);
        return "comment/update-comment";
    }


}
