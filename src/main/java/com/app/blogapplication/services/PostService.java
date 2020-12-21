package com.app.blogapplication.services;
import com.app.blogapplication.entities.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;


public interface PostService {

    void savePost(Post post);
    Post getPost(int id);
    void deletePost(Post post);
    Page<Post> getPages(String searchText,Pageable pageable);

}
