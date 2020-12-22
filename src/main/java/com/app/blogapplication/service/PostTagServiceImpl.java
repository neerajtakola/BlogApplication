package com.app.blogapplication.service;

import com.app.blogapplication.repository.PostTagRepository;
import com.app.blogapplication.entity.Post;
import com.app.blogapplication.entity.PostTag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostTagServiceImpl implements PostTagService {

    @Autowired
    private PostTagRepository postTagRepository;

    @Override
    public void savePostTag(PostTag postTag) {
        postTagRepository.save(postTag);
    }

    @Override
    public List<PostTag> getAll(Post post) {
        return postTagRepository.findAllByPost(post);
    }

    @Override
    public void deletePostTag(PostTag postTag) {
            postTagRepository.delete(postTag);
    }
}
