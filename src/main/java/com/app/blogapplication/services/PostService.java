package com.app.blogapplication.services;
import com.app.blogapplication.entities.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;


public interface PostService {

    List<Post> getPosts(String text,Pageable pageable);
    void savePost(Post post);
    Post getPost(int id);
    void deletePost(Post post);
    Page<Post> getPages(Pageable pageable);
}
