package com.app.blogapplication.services;

import com.app.blogapplication.dao.IPostRepository;
import com.app.blogapplication.entities.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PostService implements  IPostService{

    @Autowired
    private IPostRepository postRepository;

    @Override
    public List<Post> getPosts(String text, Pageable pageable) {
        return postRepository.findAll(text,pageable);
    }

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
    public Page<Post> getPages(Pageable pageable) {
       return postRepository.findAll(pageable);
    }

}
