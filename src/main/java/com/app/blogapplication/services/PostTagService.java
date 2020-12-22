package com.app.blogapplication.services;

import com.app.blogapplication.entities.Post;
import com.app.blogapplication.entities.PostTag;

import java.util.List;

public interface PostTagService {
    void savePostTag(PostTag postTag);
    List<PostTag> getAll(Post post);
    void deletePostTag(PostTag postTag);

}
