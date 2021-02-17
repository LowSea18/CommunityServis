package com.example.Communityservice.service;

import com.example.Communityservice.Exception.NotFoundException;
import com.example.Communityservice.mapping.Mapping;
import com.example.Communityservice.model.entityy.Post;
import com.example.Communityservice.model.postDto.PostDto;
import com.example.Communityservice.model.entityy.User;
import com.example.Communityservice.model.postDto.PostUserDto;
import com.example.Communityservice.model.userDto.UserInPost;
import com.example.Communityservice.repository.PostRepository;
import com.example.Communityservice.repository.UserReposiotory;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    @Autowired
    private final PostRepository postRepository;
    @Autowired
    private final UserReposiotory userReposiotory;
    @Autowired
    private final UserService userService;
    @Autowired
    private final Mapping mapping;

    public PostService(PostRepository postRepository,UserReposiotory userReposiotory,UserService userService,Mapping mapping){
        this.postRepository=postRepository;
        this.userReposiotory=userReposiotory;
        this.userService=userService;
        this.mapping=mapping;
    }

    public void addPostService ( String username, String postBody){
        User userFromdb = userReposiotory.findByusername(username).orElseThrow(()->new NotFoundException("Can not found User " + username));
        Post post = new Post();
        post.setBody(postBody);
        post.setUser(userFromdb);
        postRepository.save(post);
        System.out.println("Post created");
    }

    public void updatePost(Integer id, String body){
        Post post = postRepository.findById(id).orElseThrow(() -> new NotFoundException("Post does not exist id," +id));
        post.setBody(body);
        postRepository.save(post);
    }

    public List<PostDto> showPosts(){
        List<PostDto> postDtos = new ArrayList<>();
        postRepository.findAll().forEach(p -> postDtos.add(mapping.mapPostToPostDto(p)));
        return postDtos;
    }

    public void deletePost(Integer id){
        Post postFromDb = postRepository.findById(id).orElseThrow(() -> new NotFoundException("Post does not exist"));
        postRepository.delete(postFromDb);
    }




}
