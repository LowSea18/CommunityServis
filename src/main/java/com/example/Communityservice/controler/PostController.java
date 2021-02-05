package com.example.Communityservice.controler;

import com.example.Communityservice.model.postDto.PostDto;
import com.example.Communityservice.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping("/posts")
    public void addPostService (@RequestHeader(name = "username") String username, @RequestBody String postbody){
        postService.addPostService(username,postbody);
    }

    @GetMapping("/posts")
    public List<PostDto> showAll(){
        return postService.showPosts();
    }

    @PutMapping("post/{id}")
    public void updatePost(@PathVariable Integer id, @RequestBody String body){
        postService.updatePost(id,body);
    }

}
