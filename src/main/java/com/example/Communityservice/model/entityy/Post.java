package com.example.Communityservice.model.entityy;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "posts")
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NonNull
    @ManyToOne
    private User user;

    @NonNull
    private String body;
}
