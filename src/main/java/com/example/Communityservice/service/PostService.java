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
    User user;

    @PostMapping("/posts")
    public ResponseEntity addPost (@RequestHeader(name = "username") String username, @RequestBody String postbody){
        Optional<User> userFromdb = userReposiotory.findByusername(username);
        if(userFromdb.isEmpty()){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        Post post = new Post(userFromdb.get(), postbody);
        userFromdb.get().setNumersOfPosts(userFromdb.get().getNumersOfPosts()+1);
        Post savedpost = postRepository.save(post);
        return ResponseEntity.ok(savedpost);
    }


}
