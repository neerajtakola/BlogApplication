package com.app.blogapplication.services;

import com.app.blogapplication.dao.IPostRepository;
import com.app.blogapplication.entities.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService implements  IPostService{

    @Autowired
    private IPostRepository postRepository;

    @Override
    public void savePost(Post post) {
        postRepository.save(post);
    }
    @Override
    public Post showPostById(int id) {
        return postRepository.getOne(id);
    }


    @Override
    public void deletePostById(int id) {
        postRepository.deleteById(id);
    }

    @Override
    public List<Post> getPosts(String searchText) {
        return postRepository.findAllByText(searchText);
    }

}
