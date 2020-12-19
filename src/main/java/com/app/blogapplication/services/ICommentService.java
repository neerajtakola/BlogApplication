package com.app.blogapplication.services;

import com.app.blogapplication.entities.Comment;

import java.util.List;

public interface ICommentService {
    void updateComment(Comment comment);
    void deleteComment(int id);
    void addComment(Comment comment);
    Comment getCommentById(int id);
    List<Comment> getCommentsOfPost(int id);
}
