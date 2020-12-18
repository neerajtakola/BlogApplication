package com.app.blogapplication.services;

import com.app.blogapplication.dao.PostRepository;
import com.app.blogapplication.entities.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public List<Post> getPosts(){
        return postRepository.findAll();
    }

    public void saveOrUpdatePost(Post post){
        postRepository.save(post);
    }

    public void deletePost(int id){
        postRepository.deleteById(id);
    }
}
