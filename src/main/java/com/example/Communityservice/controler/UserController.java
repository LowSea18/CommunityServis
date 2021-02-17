package com.example.Communityservice.controler;


import com.example.Communityservice.model.CreateUserDto.CreateUserDto;
import com.example.Communityservice.model.LoginRequestDto.LoginRequestDto;
import com.example.Communityservice.model.entityy.User;
import com.example.Communityservice.model.userDto.UpdateUserDto;
import com.example.Communityservice.model.userDto.UserDto;
import com.example.Communityservice.model.userDto.UserWithPostDto;
import com.example.Communityservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public List<UserDto> getAllUsers(){
        return userService.getAllUsers();
    }


    @GetMapping("/users/posts")
    public List<UserWithPostDto> getAllUsersWithPost(){
        return userService.getUsersWithPostsService();
    }

    @GetMapping("/users/{id}")
    public UserDto getUserById(@PathVariable Long id){
        return userService.getUserByIdService(id);

    }

    @PostMapping("/users")
    public void addUser(@RequestBody CreateUserDto body) {

        userService.addUserService(body);
    }

    @PostMapping("/login")
    public void login(@RequestBody LoginRequestDto credentials){
        userService.loginService(credentials);
    }

    @PutMapping("/user/{id}")
    public void updateUser(@PathVariable Long id, @RequestBody UpdateUserDto updateUserDto){
        userService.updateUser(id,updateUserDto);
    }

    @DeleteMapping("/user/{id}")
    public void deleteUser(@PathVariable(name = "id") Long id){
        userService.deleteUser(id);
    }


}
