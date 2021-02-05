package com.example.Communityservice.service;

import com.example.Communityservice.Exception.AlreadyExistException;
import com.example.Communityservice.Exception.FailedToLoginException;
import com.example.Communityservice.Exception.NotFoundException;
import com.example.Communityservice.Exception.WrongAgeException;
import com.example.Communityservice.model.CreateUserDto.CreateUserDto;
import com.example.Communityservice.model.LoginRequestDto.LoginRequestDto;
import com.example.Communityservice.model.entityy.User;
import com.example.Communityservice.model.userDto.UpdateUserDto;
import com.example.Communityservice.model.userDto.UserDto;
import com.example.Communityservice.model.userDto.UserWithPostDto;
import com.example.Communityservice.model.userDto.UserInPost;
import com.example.Communityservice.repository.UserReposiotory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserReposiotory reposiotory;

    private final PostService postService;

    public List<UserDto> getAllUsers(){
        List<UserDto> userDtos = new ArrayList<>();
        reposiotory.findAll().forEach(u -> userDtos.add(mapUserToUserDto(u)));
        return userDtos;
    }

    public List<UserWithPostDto> getUsersWithPostsService(){
        List<UserWithPostDto> users = new ArrayList<>();
        reposiotory.findAll().forEach(u -> users.add(mapUserToUserWithPostDto(u)));
        return users;
    }

    public User getUserByIdService(Long id){
        User userFromDb = reposiotory.findById(id).orElseThrow(() -> new NotFoundException("Can not found User, id: "+id));

        return userFromDb;
    }

    public void addUserService(CreateUserDto body) {

        if(body.getAge()<=0){
            throw new WrongAgeException("Age must be greater than zero");
        }else {

            Optional<User> userFromDb = reposiotory.findByusername(body.getUsername());
            if (userFromDb.isPresent()) {
                throw new AlreadyExistException("User with this username already exist");
            } else
                reposiotory.save(mapCreateDtoToUser(body));
            System.out.println("Created");
        }
    }

    public void loginService (LoginRequestDto user){
        User userFromDb = reposiotory.findByusername(user.getUsername()).orElseThrow(() ->new NotFoundException(""));
        if(!userFromDb.getPassword().equals(user.getPassword()) ){
            throw new FailedToLoginException("Login Failed");
        }
        System.out.println("Login Complete");
    }

    public void updateUser(Long id , UpdateUserDto updateUserDto){
       User user = reposiotory.findById(id).orElseThrow( ()-> new NotFoundException("User does not exist, id:" + id));
        user.setUsername(updateUserDto.getUsername());
        user.setPassword(updateUserDto.getPassword());
        user.setAge(updateUserDto.getAge());
        reposiotory.save(user);
    }


    public User mapCreateDtoToUser(CreateUserDto createUserDto){
        User user = new User();
        user.setUsername(createUserDto.getUsername());
        user.setPassword(createUserDto.getPassword());
        user.setAge(createUserDto.getAge());
        return user;
    }

    public UserWithPostDto mapUserToUserWithPostDto(User user){
        UserWithPostDto userDto = new UserWithPostDto();
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setAge(user.getAge());
        userDto.setPosts(postService.mapListPostToListDtoToPost(user.getPosts()));
        return userDto;
    }

    public UserWithPostDto mapUserDtoInPostToUser (UserInPost userInPost){
       UserWithPostDto user = new UserWithPostDto();
        user.setUsername(userInPost.getUsername());

        return user;
    }

    public UserDto mapUserToUserDto(User user){
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setAge(user.getAge());
        userDto.setNumberOfPosts(user.getPosts().size());
        return userDto;
    }



}
