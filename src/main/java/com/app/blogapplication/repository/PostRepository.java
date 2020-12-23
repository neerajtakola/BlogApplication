package com.app.blogapplication.repository;

import com.app.blogapplication.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
    @Query("select p from Post p where lower(p.title) like %?1% or lower(p.excerpt) like %?1% or " +
            "lower(p.content) like %?1% or p.author in (select u.id from " +
            "User u where lower(u.name) like %?1%) or p.id in " +
            "(select pt.post from PostTag pt where pt.tag in (select t.id from Tag t where t.name like %?1%) )")
    Page<Post> findAllByText(String searchText, Pageable pageable);

    @Query(value = "select * from post where author_id = ?1", nativeQuery = true)
    List<Post> findAllByAuthor(int authorId);

    @Query(value="select * from post where author_id in ?1 and id in (select post_id from post_tag where tag_id in ?2)",nativeQuery = true)
    List<Post> findAllByAuthorAndPostTags(List<Integer> authorIds, List<Integer> tagIds);

    @Query(value="select * from post where id in (select post_id from post_tag where tag_id in ?1)",nativeQuery = true)
    List<Post> findAllByTags(List<Integer> tagIds);
}
