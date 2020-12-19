package com.app.blogapplication.services;
import com.app.blogapplication.entities.Post;
import org.springframework.data.domain.Pageable;
import java.util.List;


public interface IPostService {

    List<Post> getPosts(String text,Pageable pageable);
    void savePost(Post post);
    Post getPost(int id);
    void deletePost(Post post);
    Pageable findPaginated(int pageNo, int pageSize);
}
