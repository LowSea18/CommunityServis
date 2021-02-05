package com.example.Communityservice.repository;

import com.example.Communityservice.model.entityy.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository <Post, Long> {
}
