package com.example.Communityservice.model.userDto;

import com.example.Communityservice.model.postDto.PostDto;
import lombok.Data;

import java.util.List;

@Data
public class UserWithPostDto {
    private Long id;
    private String username;
    private int age;
    private List<PostDto> posts;
}
