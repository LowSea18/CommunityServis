package com.example.Communityservice.service;

import com.example.Communityservice.model.Post;
import com.example.Communityservice.model.User;
import com.example.Communityservice.repository.PostRepository;
import com.example.Communityservice.repository.UserReposiotory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class PostService {
    @Autowired
    PostRepository postRepository;
    @Autowired
    UserReposiotory userReposiotory;

    public ResponseEntity addPostService ( String username, String postbody){
        Optional<User> userFromdb = userReposiotory.findByusername(username);
        if(userFromdb.isEmpty()){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        Post post = Post.builder()
                .user(userFromdb.get())
                .body(postbody)
                .build();

        userFromdb.get().setNumbersOfPosts(userFromdb.get().getNumbersOfPosts()+1);
        Post savedpost = postRepository.save(post);
        return ResponseEntity.ok(savedpost);
    }


}
