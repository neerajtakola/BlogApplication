package com.app.blogapplication.controller;

import com.app.blogapplication.entities.Post;
import com.app.blogapplication.entities.PostTag;
import com.app.blogapplication.entities.PostTagIdentity;
import com.app.blogapplication.entities.User;
import com.app.blogapplication.services.PostService;
import com.app.blogapplication.services.PostTagService;
import com.app.blogapplication.services.TagService;
import com.app.blogapplication.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Controller
public class PostController {

    private final int PAGE_SIZE=2;
    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    @Autowired
    private TagService tagService;

    @Autowired
    private PostTagService postTagService;

    @RequestMapping(path ={"/","/page/{pageNo}"})
    public String showPosts(Model model,@RequestParam Optional<String> searchText,@PathVariable Optional<Integer> pageNo){
        Pageable pageable = PageRequest.of(pageNo.orElse(0),PAGE_SIZE);
        Page<Post> pages = postService.getPages(pageable);
        model.addAttribute("totalPages", pages.getTotalPages());
        model.addAttribute("posts",postService.getPosts(searchText.orElse("_"),pageable));
        return "home";
    }

    @GetMapping("/new-post")
    public String submitPost(Model model){
        model.addAttribute("tags",tagService.getAllTags());
        return "post/edit";
    }

    @PostMapping("/new-post")
    public String savePost(@RequestParam String title,@RequestParam String content, @RequestParam String excerpt,@RequestParam String email,@RequestParam Optional<List<Integer>> tags){
        Post post = new Post();
        post.setContent(content);
        post.setTitle(title);
        post.setExcerpt(excerpt);
        for(int tagId : tags.orElse(Arrays.asList(1,2,3,4))){
            PostTag postTag = new PostTag();
            postTag.setPost(post);
            postTag.setTag(tagService.getTagById(tagId));
            PostTagIdentity postTagIdentity = new PostTagIdentity();
            postTagIdentity.setPostId(post.getId());
            postTagIdentity.setTag(tagId);
            postTag.setId(postTagIdentity);
            postTagService.savePostTag(postTag);
        }
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
