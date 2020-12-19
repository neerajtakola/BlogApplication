package com.app.blogapplication.controller;

import com.app.blogapplication.entities.Post;
import com.app.blogapplication.entities.User;
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

    @Autowired
    private IPostService postService;

    @Autowired
    private IUserService userService;

    @RequestMapping("/")
    public String showPosts(Model model,@RequestParam Optional<String> searchText){
        model.addAttribute("posts",postService.getPosts(searchText.orElse("_"),postService.findPaginated(0,2)));
        return "home";
    }

    @GetMapping("/new-post")
    public String sumitPost(){
        return "post/edit";
    }

    @PostMapping("/new-post")
    public String savePost(@RequestParam String title,@RequestParam String content, @RequestParam String excerpt,@RequestParam String email){
        Post post = new Post();
        post.setContent(content);
        post.setTitle(title);
        post.setExcerpt(excerpt);
        User user = (User) userService.findUserByEmail(email);
        if(user == null){
           user = new User();
           user.setName("Guest");
           user.setEmail(email);
        }
        post.setAuthor(user);
        postService.savePost(post);
        return "redirect:/";
    }

    @RequestMapping("/update/{id}")
    public String showEditForm(Model model,@PathVariable int id){
       Post post = postService.getPost(id);
       model.addAttribute("post",post);
       return "post/update";
    }

    @PostMapping("/update/{id}")
    public String updatePost(@ModelAttribute Post post){
        postService.savePost(post);
        return "redirect:/";
    }

    @RequestMapping("/delete/{id}")
    public String deletePost(@PathVariable int id){
        Post post = postService.getPost(id);
        post.setAuthor(null);
        postService.deletePost(post);
        return "redirect:/";
    }

    @RequestMapping("/view-post/{id}")
    public String showPost(@PathVariable int id,Model model){
        model.addAttribute("post",postService.getPost(id));
        return "post/show-post";
    }

}
