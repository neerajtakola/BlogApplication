package com.app.blogapplication.controller;

import com.app.blogapplication.entities.Post;
import com.app.blogapplication.services.IPostService;
import com.app.blogapplication.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
public class PostController {

    private final IPostService postService;
    private final IUserService userService;

    @Autowired
    public PostController(IPostService postService, IUserService userService){
        this.postService = postService;
        this.userService = userService;
        }

    @RequestMapping(value = "/")
    public String showPosts(Model model, @RequestParam Optional<String> searchText){
        model.addAttribute("posts",postService.getPosts(searchText.orElse("_")));
        return "home";
    }

    @RequestMapping(value = "/view-post/{id}")
    public String showPost(@PathVariable("id") int id, Model model){
        model.addAttribute("post",postService.showPostById(id));
        return "post/show-post";
    }


    @GetMapping(value = "/new-post")
    public String newPost(){
        return "post/edit";
    }

    @PostMapping(value = "/new-post")
    public String submitPost(@RequestParam String title,@RequestParam String excerpt,@RequestParam String content) throws IOException {
        Post post = new Post();
        post.setTitle(title);
        post.setContent(content);
        post.setExcerpt(excerpt);
        post.setPublished(true);
        postService.savePost(post);
        return "redirect:/";
    }


    @RequestMapping(value = "/delete/{id}")
    public String deletePost(@PathVariable("id") int id) throws IOException {
        postService.deletePostById(id);
        return "redirect:/";
    }

    @RequestMapping(value = "/update/{id}")
    public String updatePost(@PathVariable("id") int id, Model model){
        Post post = postService.showPostById(id);
        model.addAttribute("post",post);
        return "post/update";
    }

    @PostMapping(value="/update/{id}")
        public String updatePost(@ModelAttribute("post") Post post){
        postService.savePost(post);
        return "redirect:/";
    }
}
