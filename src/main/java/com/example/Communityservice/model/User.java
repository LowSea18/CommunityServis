package com.example.Communityservice.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="persons")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String username;

    @NonNull
    private String password;

    @Column(name = "number_of_posts")
    private int numbersOfPosts;

}
