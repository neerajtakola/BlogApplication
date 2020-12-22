package com.app.blogapplication.service;

import com.app.blogapplication.entity.Tag;

import java.util.List;

public interface TagService {
    void addTag(Tag tag);
    Tag getTagById(int id);
    void deleteTagById(int id);
    List<Tag> getAllTags();
}
