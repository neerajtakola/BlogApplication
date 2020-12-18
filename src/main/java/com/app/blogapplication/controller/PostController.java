package com.app.blogapplication.controller;

import com.app.blogapplication.entities.Post;
import com.app.blogapplication.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequestMapping(value = "/show-posts")
@Controller
public class PostController {

    @Autowired
    private PostService postService;

    public PostController(){
        System.out.println("Post");
    }


    @RequestMapping(value = "/show")
    public void showPosts(){
        System.out.println("inside post");
        for(Post p : postService.getPosts()){
            System.out.println(p);
        }
    }

    @RequestMapping(value = "/update")
    public void updatePost(){
        Post post = postService.getPosts().get(0);
        post.setContent("Hi world. This content has been updated");
        postService.saveOrUpdatePost(post);
    }

    @RequestMapping(value = "/submit")
    public void submitPost(){
        Post post = new Post();
        post.setContent("This is the 3rd post");
        post.setPublished(true);
        postService.saveOrUpdatePost(post);
    }

    @RequestMapping(value = "/delete")
    public void deletePost(){
        postService.deletePost(2);
    }
}
