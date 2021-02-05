package com.example.Communityservice.model.userDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;

@Data
@AllArgsConstructor
public class UserInPost {
    @NonNull
    private String username;
}
