package com.example.Communityservice.model.entityy;

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

    private int age;
    @OneToMany(mappedBy="user")
    private List<Post> posts;

}
