package com.example.Communityservice.controler;

import com.example.Communityservice.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PostControler {

    @Autowired
    PostService postService;

    @PostMapping("/posts")
    public ResponseEntity addPostService (@RequestHeader(name = "username") String username, @RequestBody String postbody){

        return postService.addPostService(username,postbody);

    }

}
