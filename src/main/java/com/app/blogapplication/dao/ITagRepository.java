package com.app.blogapplication.dao;

import com.app.blogapplication.entities.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITagRepository extends JpaRepository<Tag, Integer> {
}
