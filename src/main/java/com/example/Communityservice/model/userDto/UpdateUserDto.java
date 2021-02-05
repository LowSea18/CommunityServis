package com.example.Communityservice.model.userDto;

import lombok.Data;

@Data
public class UpdateUserDto {
    private String username;
    private String password;
    private int age;
}
