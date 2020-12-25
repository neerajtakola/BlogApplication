package com.app.blogapplication.controller;

import com.app.blogapplication.entity.*;
import com.app.blogapplication.service.PostService;
import com.app.blogapplication.service.PostTagService;
import com.app.blogapplication.service.TagService;
import com.app.blogapplication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
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

    @RequestMapping(path = {"/"})
    public String showPosts(Model model, @RequestParam Optional<String> searchText, @RequestParam("start") Optional<Integer> pageNo, @RequestParam("limit") Optional<Integer> pageSize, @RequestParam Optional<String> sort) {
        Page<Post> pages = postService.getPages(searchText.orElse("_").toLowerCase(),
                PageRequest.of(pageNo.orElse(0), pageSize.orElse(10), sort.orElse("asc").equals("asc") ? Sort.by("publishedAt").ascending() : Sort.by("publishedAt").descending()));
        model.addAttribute("pages", pages.getTotalPages());
        model.addAttribute("posts", pages.getContent());
        return "index";
    }

    @RequestMapping("/filter")
    public String filterProcess(@RequestParam(value = "authorId") Optional<List<Integer>> authorIds, @RequestParam(value = "tagId") Optional<List<Integer>> tagIds, @RequestParam(required = false) String sortField, Model model) {
        if (authorIds.isPresent() && tagIds.isPresent()) {
            List<Post> filteredPosts = postService.getAllPostsByAuthorsAndTags(authorIds.orElse(Arrays.asList(0)),
                    tagIds.orElse(Arrays.asList(0)));
            Page<Post> pages = new PageImpl<>(filteredPosts, PageRequest.of(0, 2), filteredPosts.size());
            model.addAttribute("posts", pages.getContent());
            model.addAttribute("pages", pages.getTotalPages());
        } else if (authorIds.isPresent() && tagIds.isEmpty()) {
            List<Post> authorPosts = new ArrayList<>();
            for (int authorId : authorIds.orElse(Arrays.asList(0))) {
                List<Post> posts = postService.getAllPostsByAuthor(authorId);
                authorPosts.addAll(posts);
            }
            model.addAttribute("posts", authorPosts);
        } else {
            model.addAttribute("posts", postService.getAllPostsByTags(tagIds.orElse(Arrays.asList(0))));
        }
        return "filter/index";
    }

    @GetMapping("/search")
    public String filterProcess(@RequestParam String searchText, @RequestParam(value = "authorId") Optional<List<Integer>> authorIds, @RequestParam(value = "tagId") Optional<List<Integer>> tagIds, @RequestParam Optional<String> sortField, Model model) {
        if (authorIds.isEmpty() && tagIds.isEmpty()) {
            Page<Post> pages = postService.getPages(searchText.toLowerCase(),
                    PageRequest.of(0, 10, sortField.orElse("asc").equals("asc") ? Sort.by("publishedAt").ascending() : Sort.by("publishedAt").descending()));
            model.addAttribute("posts", pages.getContent());
            model.addAttribute("searchText", searchText);
        } else if (authorIds.isPresent() && tagIds.isEmpty()) {

            List<Post> filteredPosts = postService.getAllPostsByTextAndAuthors(searchText, authorIds.orElse(Arrays.asList(10)));
            Page<Post> pages = new PageImpl<>(filteredPosts, PageRequest.of(0, 2), filteredPosts.size());
            model.addAttribute("searchText", searchText);
            model.addAttribute("posts", pages.getContent());
        }
        return "filter/index";
    }

    @GetMapping("/new-post")
    public String submitPost(Model model) {
        model.addAttribute("tags", tagService.getAllTags());
        model.addAttribute("post", new Post());
        return "post/add";
    }

    @PostMapping("/new-post")
    public String savePost(@ModelAttribute Post post, @RequestParam String email, @RequestParam(required = false) List<Integer> tags) {
        User user = (User) userService.findUserByEmail(email);
        if (user == null) {
            user = new User();
            user.setName("Guest");
            user.setEmail(email);
        }
        post.setAuthor(user);
        postService.savePost(post);
        for (int tagId : tags) {
            Tag tag = tagService.getTagById(tagId);
            PostTag postTag = new PostTag();
            postTag.setPost(post);
            postTag.setTag(tag);
            postTagService.savePostTag(postTag);
        }
        return "redirect:/";
    }

    @RequestMapping("/update/{postId}")
    public String showEditForm(Model model, @PathVariable int postId) {
        Post post = postService.getPost(postId);
        List<Tag> tags = new ArrayList<>();
        for (PostTag postTag : post.getPostTags()) {
            tags.add(postTag.getTag());
        }
        model.addAttribute("post", postService.getPost(postId));
        model.addAttribute("postTags", tags);
        return "post/update";
    }

    @PostMapping("/update/{id}")
    public String updatePost(@ModelAttribute Post post) {
        postService.savePost(post);
        return "redirect:/";
    }

    @RequestMapping("/delete/{id}")
    public String deletePost(@PathVariable("id") int postId) {
        Post post = postService.getPost(postId);
        for (PostTag postTag : postTagService.getAll(post)) {
            postTagService.deletePostTag(postTag);
        }
        post.setAuthor(null);
        postService.deletePost(post);
        return "redirect:/";
    }

    @RequestMapping("/view-post/{id}")
    public String showPost(@PathVariable("id") int postId, Model model) {
        model.addAttribute("post", postService.getPost(postId));
        return "post/show";
    }
}
