package com.app.blogapplication.service;

import com.app.blogapplication.repository.CommentRepository;
import com.app.blogapplication.entity.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepository commentRepository;

    @Override
    public List<Comment> getCommentsOfPost(int id) {
       return commentRepository.findAll(id);
    }

    @Override
    public void updateComment(Comment comment) {
        commentRepository.save(comment);
    }

    @Override
    public void deleteComment(int id) {
        commentRepository.deleteById(id);
    }

    @Override
    public void addComment(Comment comment) {
        commentRepository.save(comment);
    }

    @Override
    public Comment getCommentById(int id) {
       return commentRepository.getOne(id);
    }

}
