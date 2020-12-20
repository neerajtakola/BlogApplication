package com.app.blogapplication.services;

import com.app.blogapplication.dao.TagRepository;
import com.app.blogapplication.entities.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagRepository tagRepository;


    @Override
    public void addTag(Tag tag) {
        tagRepository.save(tag);
    }

    @Override
    public Tag getTagById(int id) {
        return tagRepository.getOne(id);
    }

    @Override
    public void deleteTagById(int id) {
        tagRepository.deleteById(id);
    }

    @Override
    public List<Tag> getAllTags() {
        return tagRepository.findAll();
    }


}
