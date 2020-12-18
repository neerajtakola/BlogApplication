package com.app.blogapplication.dao;

import com.app.blogapplication.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICommentRepository extends JpaRepository<Comment,Integer> {
    @Query("select c from Comment c where c.post = '?1' ")
    List<Comment> findAll(int id);
}
