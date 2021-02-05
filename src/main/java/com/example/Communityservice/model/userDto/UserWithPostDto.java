package com.example.Communityservice.model.userDto;

import com.example.Communityservice.model.postDto.PostDto;
import com.example.Communityservice.model.postDto.PostUserDto;
import lombok.Data;

import java.util.List;

@Data
public class UserWithPostDto {
    private Long id;
    private String username;
    private int age;
    private List<PostUserDto> posts;
}
