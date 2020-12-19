package com.app.blogapplication.dao;

import com.app.blogapplication.entities.Post;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface IPostRepository extends JpaRepository<Post,Integer> {

    @Query("select p from Post p where p.title like %?1% or p.content like %?1% or p.excerpt like %?1%")
    List<Post> findAll(String searchText, Pageable pageable);
}
