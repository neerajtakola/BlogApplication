package com.app.blogapplication.services;

import com.app.blogapplication.dao.PostRepository;
import com.app.blogapplication.dao.PostTagRepository;
import com.app.blogapplication.entities.Post;
import com.app.blogapplication.entities.PostTag;
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
