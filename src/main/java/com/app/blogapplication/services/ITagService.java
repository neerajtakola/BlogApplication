package com.app.blogapplication.services;

import com.app.blogapplication.entities.Tag;

import java.util.List;

public interface ITagService {
    void addTag(Tag tag);
    Tag getTagById(int id);
    void deleteTagById(int id);
    List<Tag> getAllTags();
}
