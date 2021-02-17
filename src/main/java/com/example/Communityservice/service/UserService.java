package com.example.Communityservice.service;

import com.example.Communityservice.Exception.AlreadyExistException;
import com.example.Communityservice.Exception.FailedToLoginException;
import com.example.Communityservice.Exception.NotFoundException;
import com.example.Communityservice.Exception.WrongAgeException;
import com.example.Communityservice.mapping.Mapping;
import com.example.Communityservice.model.CreateUserDto.CreateUserDto;
import com.example.Communityservice.model.LoginRequestDto.LoginRequestDto;
import com.example.Communityservice.model.entityy.User;
import com.example.Communityservice.model.userDto.UpdateUserDto;
import com.example.Communityservice.model.userDto.UserDto;
import com.example.Communityservice.model.userDto.UserWithPostDto;
import com.example.Communityservice.model.userDto.UserInPost;
import com.example.Communityservice.repository.PostRepository;
import com.example.Communityservice.repository.UserReposiotory;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service

public class UserService {
    @Autowired
    private final UserReposiotory reposiotory;
    @Autowired
    private final Mapping mapping;
    @Autowired
    private final PostRepository postRepository;

    public UserService(UserReposiotory userReposiotory,Mapping mapping,PostRepository postRepository){
       this.reposiotory=userReposiotory;
       this.mapping=mapping;
       this.postRepository=postRepository;
    }

    public List<UserDto> getAllUsers(){
        List<UserDto> userDtos = new ArrayList<>();
        reposiotory.findAll().forEach(u -> userDtos.add(mapping.mapUserToUserDto(u)));
        return userDtos;
    }

    public List<UserWithPostDto> getUsersWithPostsService(){
        List<UserWithPostDto> users = new ArrayList<>();
        reposiotory.findAll().forEach(u -> users.add(mapping.mapUserToUserWithPostDto(u)));
        return users;
    }

    public UserDto getUserByIdService(Long id){
        User userFromDb = reposiotory.findById(id).orElseThrow(() -> new NotFoundException("Can not found User, id: "+id));
        return mapping.mapUserToUserDto(userFromDb);
    }

    public void addUserService(CreateUserDto body) {

        if(body.getAge()<18){
            throw new WrongAgeException("Age must be greater than 18");
        }else {
            Optional <User> userFromDb = reposiotory.findByusername(body.getUsername());
            if(userFromDb.isPresent()){
                throw new AlreadyExistException("User with this username already exist");
            }else
            reposiotory.save(mapping.mapCreateDtoToUser(body));
        }

    }

    public void loginService (LoginRequestDto user){
        User userFromDb = reposiotory.findByusername(user.getUsername()).orElseThrow(() ->new NotFoundException("Login Failed"));
        if(!userFromDb.getPassword().equals(user.getPassword()) ){
            throw new FailedToLoginException("Login Failed");
        }

    }

    public void updateUser(Long id , UpdateUserDto updateUserDto){
        if(updateUserDto.getAge()<18){
            throw new WrongAgeException("Age must be greater than 18");
        }else {
        User user = reposiotory.findById(id).orElseThrow( ()-> new NotFoundException("User does not exist, id:" + id));
        user.setUsername(updateUserDto.getUsername());
        user.setPassword(updateUserDto.getPassword());
        user.setAge(updateUserDto.getAge());
        reposiotory.save(user);
        }
    }

    public void deleteUser(Long id){
        User user = reposiotory.findById(id).orElseThrow( ()-> new NotFoundException("User does not exist, id:" + id));
        if (!user.getPosts().isEmpty()) {
            user.getPosts().forEach(postRepository::delete);
        }
        reposiotory.delete(user);

    }



}
