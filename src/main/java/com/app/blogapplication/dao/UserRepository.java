package com.app.blogapplication.dao;

import com.app.blogapplication.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("select u from User u where u.email like ?1")
    User findByEmailLike(String email);
}
