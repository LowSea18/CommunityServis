package com.example.Communityservice.model.userDto;

import lombok.Data;

@Data
public class UserDto {
    private Long id;
    private String username;
    private int age;
    private int numberOfPosts;
}
