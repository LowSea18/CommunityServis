package com.example.Communityservice.repository;

import com.example.Communityservice.model.entityy.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserReposiotory extends JpaRepository<User, Long> {

     Optional<User>  findByusername(String username);

}
