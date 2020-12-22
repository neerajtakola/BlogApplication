package com.app.blogapplication.repository;

import com.app.blogapplication.entity.Post;
import com.app.blogapplication.entity.PostTag;
import com.app.blogapplication.entity.PostTagIdentity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostTagRepository extends JpaRepository<PostTag, PostTagIdentity> {
    List<PostTag> findAllByPost(Post post);
}
