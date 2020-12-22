package com.app.blogapplication.controller;

import com.app.blogapplication.entity.Comment;
import com.app.blogapplication.entity.Post;
import com.app.blogapplication.service.CommentService;
import com.app.blogapplication.service.PostService;
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
    public String showComments(Model model, @PathVariable("id") int postId) {
        model.addAttribute("post", postService.getPost(postId));
        model.addAttribute("comments", commentService.getCommentsOfPost(postId));
        return "comment/show";
    }

    @GetMapping(value = "/add-comment/{id}")
    public String commentForm(@PathVariable("id") int postId, Model model) {
        model.addAttribute("post", postId);
        model.addAttribute("comment", new Comment());
        return "comment/add";
    }

    @PostMapping(value = "/add-comment/{id}")
    public String submitComment(@PathVariable("id") int postId, @ModelAttribute Comment comment) {
        Post post = postService.getPost(postId);
        comment.setPost(post);
        commentService.addComment(comment);
        return "redirect:/";
    }

    @GetMapping(value = "/update-comment/{id}")
    public String updateComment(@PathVariable("id") int commentId, Model model) {
        Comment comment = commentService.getCommentById(commentId);
        model.addAttribute("comment", comment);
        return "comment/update";
    }

    @PostMapping(value = "/update-comment/{id}")
    public String updateComment(@ModelAttribute Comment comment) {
        commentService.updateComment(comment);
        return "redirect:/";
    }

    @RequestMapping("/delete-comment/{id}")
    public String deleteComment(@PathVariable("id") int commentId) {
        commentService.deleteComment(commentId);
        return "redirect:/";
    }
}
