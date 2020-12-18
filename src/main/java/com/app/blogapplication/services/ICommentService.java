package com.app.blogapplication.services;

import com.app.blogapplication.entities.Comment;

import java.util.List;

public interface ICommentService {
    List<Comment> getComments(int id);
    void updateComment(Comment comment);
    void deleteComment(int id);
    void addComment(Comment comment);

    Comment showPostById(int id);
}
