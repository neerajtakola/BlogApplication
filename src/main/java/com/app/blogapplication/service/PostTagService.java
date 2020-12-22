package com.app.blogapplication.service;

import com.app.blogapplication.entity.Post;
import com.app.blogapplication.entity.PostTag;

import java.util.List;

public interface PostTagService {
    void savePostTag(PostTag postTag);
    List<PostTag> getAll(Post post);
    void deletePostTag(PostTag postTag);

}
