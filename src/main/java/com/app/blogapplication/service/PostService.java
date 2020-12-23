package com.app.blogapplication.service;

import com.app.blogapplication.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface PostService {

    void savePost(Post post);

    Post getPost(int id);

    void deletePost(Post post);

    Page<Post> getPages(String searchText, Pageable pageable);

    List<Post> getAllPostsByAuthor(Integer authorId);

    List<Post> getAllPostsByAuthorsAndTags(List<Integer> authorIds, List<Integer> tagIds);

    List<Post> getAllPostsByTags(List<Integer> tagIds);

}
