package com.app.blogapplication.service;

import com.app.blogapplication.repository.PostRepository;
import com.app.blogapplication.entity.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Override
    public void savePost(Post post) {
        postRepository.save(post);
    }

    @Override
    public Post getPost(int id) {
        return postRepository.getOne(id);
    }

    @Override
    public void deletePost(Post post) {
        postRepository.delete(post);
    }

    @Override
    public Page<Post> getPages(String searchText, Pageable pageable) {
        return postRepository.findAllByText(searchText, pageable);
    }

    @Override
    public List<Post> getAllPostsByAuthor(Integer authorId) {
        return postRepository.findAllByAuthor(authorId);
    }

    @Override
    public List<Post> getAllPostsByAuthorsAndTags(List<Integer> authorIds, List<Integer> tagIds) {
        return postRepository.findAllByAuthorAndPostTags(authorIds, tagIds);
    }

    @Override
    public List<Post> getAllPostsByTags(List<Integer> tagIds) {
        return postRepository.findAllByTags(tagIds);
    }

    @Override
    public List<Post> getAllPostsByTextAndAuthorsAndTags(String searchText, List<Integer> authorIds, List<Integer> tagIds) {
        return postRepository.findAllByTextAndAuthorAndPostTags(searchText,authorIds,tagIds);
    }

    @Override
    public List<Post> getAllPostsByTextAndAuthors(String searchText, List<Integer> authorIds) {
        return postRepository.findAllByTextAndAuthors(searchText,authorIds);
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

}
