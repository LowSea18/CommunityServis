package com.example.Communityservice.repository;

import com.example.Communityservice.model.entityy.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostRepository extends JpaRepository <Post, Long> {
    Optional<Post> findById(Integer aLong);
    Optional<Post> findByBody(String body);
}
