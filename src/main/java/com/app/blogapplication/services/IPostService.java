package com.app.blogapplication.services;

import com.app.blogapplication.entities.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IPostService {
    void savePost(Post post);
    Post showPostById(int id);
    void deletePostById(int id);
    List<Post> getPosts(String searchText);
}
