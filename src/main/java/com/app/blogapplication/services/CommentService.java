package com.app.blogapplication.services;

import com.app.blogapplication.dao.ICommentRepository;
import com.app.blogapplication.entities.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService implements ICommentService{
    @Autowired
    private ICommentRepository commentRepository;

    @Override
    public List<Comment> getComments(int id) {
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
    public Comment showPostById(int id) {
       return commentRepository.getOne(id);
    }
}
