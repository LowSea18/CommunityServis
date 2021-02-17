package com.example.Communityservice.mapping;

import com.example.Communityservice.model.CreateUserDto.CreateUserDto;
import com.example.Communityservice.model.entityy.Post;
import com.example.Communityservice.model.entityy.User;
import com.example.Communityservice.model.postDto.PostDto;
import com.example.Communityservice.model.postDto.PostUserDto;
import com.example.Communityservice.model.userDto.UserDto;
import com.example.Communityservice.model.userDto.UserInPost;
import com.example.Communityservice.model.userDto.UserWithPostDto;
import com.example.Communityservice.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class Mapping {

    public UserDto mapUserToUserDto(User user){
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setAge(user.getAge());
        userDto.setNumberOfPosts(user.getPosts().size());
        return userDto;
    }
    public UserWithPostDto mapUserDtoInPostToUser (UserInPost userInPost){
        UserWithPostDto user = new UserWithPostDto();
        user.setUsername(userInPost.getUsername());

        return user;
    }
    public UserWithPostDto mapUserToUserWithPostDto(User user){
        Mapping mapping =new Mapping();
        UserWithPostDto userDto = new UserWithPostDto();
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setAge(user.getAge());
        userDto.setPosts(mapping.mapListPostToListDtoToPost(user.getPosts()));
        return userDto;
    }
    public User mapCreateDtoToUser(CreateUserDto createUserDto){
        User user = new User();
        user.setUsername(createUserDto.getUsername());
        user.setPassword(createUserDto.getPassword());
        user.setAge(createUserDto.getAge());
        return user;
    }
    public List<PostUserDto> mapListPostToListDtoToPost(List<Post> posts) {
        Mapping mapping = new Mapping();
        List <PostUserDto> postDtos = new ArrayList<>();
        posts.forEach(p -> postDtos.add(mapping.mapPostDtoToPostUserDto(p)));
        return postDtos;
    }
    public PostUserDto mapPostDtoToPostUserDto(Post post){
        PostUserDto postUserDto = new PostUserDto();
        postUserDto.setId(post.getId());
        postUserDto.setBody(post.getBody());
        return postUserDto;

    }
    public PostDto mapPostToPostDto(Post post){
        PostDto postDto = new PostDto();
        postDto.setBody(post.getBody());
        postDto.setId(post.getId());
        postDto.setUserId(post.getUser().getId());
        return postDto;
    }

}
