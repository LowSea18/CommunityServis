package com.example.Communityservice.controler;


import com.example.Communityservice.model.User;
import com.example.Communityservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserControler {

    @Autowired
    UserService userService;

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers(){
        return userService.getUsersService();
    }

    @GetMapping("/users/{id}")
    public ResponseEntity getUserById(@PathVariable Long id){
        return userService.getUserByIdService(id);

    }

    @PostMapping("/users")
    public ResponseEntity addUser(@RequestBody User body) {

        return  userService.addUserService(body);
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody User user){
        return userService.loginService(user);
    }



}
