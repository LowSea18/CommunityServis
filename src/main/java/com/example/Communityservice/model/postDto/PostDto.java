package com.example.Communityservice.model.postDto;

import com.example.Communityservice.model.userDto.UserInPost;
import lombok.Data;

@Data
public class PostDto {
    private int id;
    private String body;
    private Long userId;
}
