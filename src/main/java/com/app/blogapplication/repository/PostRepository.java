package com.app.blogapplication.repository;

import com.app.blogapplication.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
    @Query("select p from Post p where lower(p.title) like %?1% or lower(p.excerpt) like %?1% or lower(p.content) like %?1% or  p.author in (select u.id from User u where lower(u.name) like %?1%)")
    Page<Post> findAllByText(String searchText, Pageable pageable);

    @Query(value = "select * from post where author_id = ?1", nativeQuery = true)
    List<Post> findAllByAuthor(int authorId);
}
