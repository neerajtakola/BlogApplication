package com.app.blogapplication.dao;

import com.app.blogapplication.entities.Post;
import com.app.blogapplication.entities.PostTag;
import com.app.blogapplication.entities.PostTagIdentity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostTagRepository extends JpaRepository<PostTag, PostTagIdentity> {
    List<PostTag> findAllByPost(Post post);
}
