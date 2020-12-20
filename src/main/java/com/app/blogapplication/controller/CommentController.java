package com.app.blogapplication.controller;

import com.app.blogapplication.entities.Comment;
import com.app.blogapplication.entities.Post;
import com.app.blogapplication.services.ICommentService;
import com.app.blogapplication.services.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class CommentController {
    @Autowired
    private ICommentService commentService;

    @Autowired
    private IPostService postService;

    @RequestMapping("/comments/{id}")
    public String showComments(Model model,@PathVariable int id){
        model.addAttribute("post",postService.getPost(id));
        model.addAttribute("comments",commentService.getCommentsOfPost(id));
        return "comment/show-comments";
    }

    @GetMapping(value="/add-comment/{post-id}")
    public String showCommentForm(@PathVariable("post-id") int id, Model model){
        Comment comment = new Comment();
        model.addAttribute("post",id);
        model.addAttribute("comment",comment);
        return "comment/add-comment";
    }

    @PostMapping(value="/add-comment/{id}")
    public String submitComment(@PathVariable int id,@RequestParam String name,@RequestParam String email,@RequestParam String content){
        Comment comment = new Comment();
        comment.setName(name);
        comment.setEmail(email);
        comment.setContent(content);
        Post post = postService.getPost(id);
        comment.setPost(post);
        commentService.addComment(comment);
        return "redirect:/";
    }

    @GetMapping(value="/update-comment/{id}")
    public String updateComment(@PathVariable int id, Model model){
        Comment comment = commentService.getCommentById(id);
        model.addAttribute("comment",comment);
        return "comment/update-comment";
    }

    @PostMapping(value="/update-comment/{id}")
    public String updateComment(@ModelAttribute("comment") Comment comment){
        commentService.updateComment(comment);
        return "redirect:/";
    }
}
