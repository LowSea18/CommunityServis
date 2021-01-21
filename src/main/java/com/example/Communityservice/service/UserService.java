package com.example.Communityservice.service;

import com.example.Communityservice.model.User;
import com.example.Communityservice.repository.UserReposiotory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
public class UserService {

    UserReposiotory reposiotory;

    public UserService(UserReposiotory reposiotory){
        this.reposiotory = reposiotory;
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers(){
         return ResponseEntity.ok(reposiotory.findAll());

    }

    @GetMapping("/users/{id}")
    public ResponseEntity getUserByBd(@PathVariable Long id){
        Optional<User> userFromDb = reposiotory.findById(id);
        if (userFromDb.isEmpty()){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
        }
        return ResponseEntity.ok(reposiotory.findById(id));
    }

    @PostMapping("/users")
    public ResponseEntity addUser(@RequestBody User body) {

        Optional <User> userFromDb = reposiotory.findByusername(body.getUsername());
        if(userFromDb.isPresent()){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
        }

        User saveUser = reposiotory.save(body);
        return ResponseEntity.ok(saveUser);
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody User user){
        Optional<User> userFromDb = reposiotory.findByusername(user.getUsername());
        if(userFromDb.isEmpty() || wrongpassword(userFromDb , user) ){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.ok().build();
    }

    private boolean wrongpassword(Optional<User> userFromdb , User user){
        return !userFromdb.get().getPassword().equals(user.getPassword());
    }

}
