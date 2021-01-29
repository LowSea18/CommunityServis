package com.example.Communityservice.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "posts")
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NonNull
    @ManyToOne
    User user;

    @NonNull
    private String body;
}
