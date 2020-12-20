package com.app.blogapplication.dao;

import com.app.blogapplication.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Integer> {
    @Query(value ="select * from comment  where post_id = :id",nativeQuery = true)
    List<Comment> findAll(@Param("id") int id);
}
