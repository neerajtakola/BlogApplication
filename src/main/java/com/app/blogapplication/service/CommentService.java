package com.app.blogapplication.service;

import com.app.blogapplication.entity.Comment;

import java.util.List;

public interface CommentService {
    void updateComment(Comment comment);
    void deleteComment(int id);
    void addComment(Comment comment);
    Comment getCommentById(int id);
    List<Comment> getCommentsOfPost(int id);
}
