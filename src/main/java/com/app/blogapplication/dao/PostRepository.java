package com.app.blogapplication.dao;

import com.app.blogapplication.entities.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post,Integer> {
    @Query("select p from Post p where p.title like %?1% or p.excerpt like %?1% or p.content like %?1%")
    Page<Post> findAllByText(String searchText, Pageable pageable);
}
