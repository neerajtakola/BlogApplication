package com.app.blogapplication.controller;

import com.app.blogapplication.entities.Comment;
import com.app.blogapplication.entities.Post;
import com.app.blogapplication.services.CommentService;
import com.app.blogapplication.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class CommentController {

    private CommentService commentService;
    private PostService postService;

    @Autowired
    public CommentController(CommentService commentService, PostService postService) {
        this.commentService = commentService;
        this.postService = postService;
    }

    @RequestMapping("/comments/{id}")
    public String showComments(Model model,@PathVariable int postId){
        model.addAttribute("post",postService.getPost(postId));
        model.addAttribute("comments",commentService.getCommentsOfPost(postId));
        return "comment/show";
    }

    @GetMapping(value="/add-comment/{post-id}")
    public String commentForm(@PathVariable int postId, Model model){
        model.addAttribute("post",postId);
        model.addAttribute("comment",new Comment());
        return "comment/add";
    }

    @PostMapping(value="/add-comment/{id}")
    public String submitComment(@PathVariable int postId,@ModelAttribute Comment comment){
        Post post = postService.getPost(postId);
        comment.setPost(post);
        commentService.addComment(comment);
        return "redirect:/";
    }

    @GetMapping(value="/update-comment/{id}")
    public String updateComment(@PathVariable int commentId, Model model){
        Comment comment = commentService.getCommentById(commentId);
        model.addAttribute("comment",comment);
        return "comment/update";
    }

    @PostMapping(value="/update-comment/{id}")
    public String updateComment(@ModelAttribute Comment comment){
        commentService.updateComment(comment);
        return "redirect:/";
    }
}
