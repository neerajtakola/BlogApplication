package com.app.blogapplication.controller;

import com.app.blogapplication.entities.*;
import com.app.blogapplication.services.PostService;
import com.app.blogapplication.services.PostTagService;
import com.app.blogapplication.services.TagService;
import com.app.blogapplication.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class PostController {

    private final PostService postService;
    private final UserService userService;
    private final TagService tagService;
    private final PostTagService postTagService;

    @Autowired
    public PostController(PostService postService, UserService userService, TagService tagService, PostTagService postTagService) {
        this.postService = postService;
        this.userService = userService;
        this.tagService = tagService;
        this.postTagService = postTagService;
    }

    @RequestMapping(path ="/")
    public String showPosts(Model model, @RequestParam Optional<String> searchText, @RequestParam("start") Optional<Integer> pageNo, @RequestParam("limit") Optional<Integer> pageSize,@RequestParam Optional<String> sort){
        Page<Post> pages = postService.getPages(searchText.orElse("_").toLowerCase(),
                PageRequest.of(pageNo.orElse(0),pageSize.orElse(10),sort.orElse("asc").equals("asc") ?Sort.by("publishedAt").ascending():Sort.by("publishedAt").descending()));
        model.addAttribute("pages", pages.getTotalPages());
        model.addAttribute("posts",pages.getContent());
        return "index";
    }

    @RequestMapping("/filter")
    public String showParams(@RequestParam(required=false) Integer authorId, @RequestParam(required = false) int tagId,Model model){
        model.addAttribute("posts",postService.getPostsByAuthor(authorId));
        return "filter/index";
    }


    @GetMapping("/new-post")
    public String submitPost(Model model){
        model.addAttribute("tags",tagService.getAllTags());
        model.addAttribute("post",new Post());
        return "post/add";
    }

    @PostMapping("/new-post")
    public String savePost(@ModelAttribute Post post,@RequestParam String email,@RequestParam(required = false) List<Integer> tags){
        User user = (User) userService.findUserByEmail(email);
        if(user == null){
            user = new User();
            user.setName("Guest");
            user.setEmail(email);
        }
        post.setAuthor(user);
        postService.savePost(post);
        for(int tagId : tags) {
            Tag tag = tagService.getTagById(tagId);
            PostTag postTag = new PostTag();
            postTag.setPost(post);
            postTag.setTag(tag);
            postTagService.savePostTag(postTag);
        }
        return "redirect:/";
    }

    @RequestMapping("/update/{postId}")
    public String showEditForm(Model model,@PathVariable int postId){
       model.addAttribute("post",postService.getPost(postId));
       return "post/update";
    }

    @PostMapping("/update/{id}")
    public String updatePost(@ModelAttribute Post post){
        postService.savePost(post);
        return "redirect:/";
    }

    @RequestMapping("/delete/{id}")
    public String deletePost(@PathVariable("id") int postId){
        Post post = postService.getPost(postId);
        for(PostTag postTag : postTagService.getAll(post)){
            postTagService.deletePostTag(postTag);
        }
        post.setAuthor(null);
        postService.deletePost(post);
        return "redirect:/";
    }

    @RequestMapping("/view-post/{id}")
    public String showPost(@PathVariable("id") int postId,Model model){
        model.addAttribute("post",postService.getPost(postId));
        return "post/show-post";
    }
}
