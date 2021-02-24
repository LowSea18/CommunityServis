package com.example.Communityservice.tests;


import com.example.Communityservice.model.CreateUserDto.CreateUserDto;
import com.example.Communityservice.model.entityy.User;
import com.example.Communityservice.repository.PostRepository;
import com.example.Communityservice.repository.UserReposiotory;
import com.example.Communityservice.service.PostService;
import com.example.Communityservice.service.UserService;
import liquibase.pro.packaged.C;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class IntegrationTestWithH2InMem {
    @Autowired
    private UserService userService;
    @Autowired
    private PostService postService;
    @Autowired
    private UserReposiotory userReposiotory;
    @Autowired
    private PostRepository postRepository;


    @BeforeEach
    public void setUp(){
        CreateUserDto createUserDto = new CreateUserDto();
        CreateUserDto createUserDto1 = new CreateUserDto();
        createUserDto.setUsername("Jone");
        createUserDto1.setUsername("Jacek");
        createUserDto.setPassword("qwerty");
        createUserDto1.setPassword("gugu123");
        createUserDto.setAge(33);
        createUserDto1.setAge(44);
        userService.addUserService(createUserDto);
        userService.addUserService(createUserDto1);
        postService.addPostService("Jone","Hi im Jone!");
        postService.addPostService("Jone","Check my github");
        postService.addPostService("Jacek","Hi im Jacek!");

    }

    @Test
     void if_user_createPost_hisValueof_numersOfPost_shouldIncrease(){
        int before = userReposiotory.findByusername("Jone").get().getPosts().size();

        postService.addPostService("Jone","Its my new boyfriend!");

        Assertions.assertEquals(before+1,userReposiotory.findByusername("Jone").get().getPosts().size());
    }

    @Test
    void if_user_createPost_hisValueof_numersOfPost_shouldDecrease(){
        int before = userReposiotory.findByusername("Jone").get().getPosts().size();

        postService.deletePost(postRepository.findByBody("Hi im Jone!").get().getId());

        Assertions.assertEquals(before-1,userReposiotory.findByusername("Jone").get().getPosts().size());
    }



    @AfterEach
    public void cleanDb(){
        postRepository.deleteAll();
        userReposiotory.deleteAll();
    }


}
