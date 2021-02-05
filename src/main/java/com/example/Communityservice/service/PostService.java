package com.example.Communityservice.service;

import com.example.Communityservice.Exception.NotFoundException;
import com.example.Communityservice.model.entityy.Post;
import com.example.Communityservice.model.postDto.PostDto;
import com.example.Communityservice.model.entityy.User;
import com.example.Communityservice.model.userDto.UserInPost;
import com.example.Communityservice.repository.PostRepository;
import com.example.Communityservice.repository.UserReposiotory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserReposiotory userReposiotory;
    private UserService userService;

    public void addPostService ( String username, String postBody){
        Optional<User> userFromdb = userReposiotory.findByusername(username);
        if(userFromdb.isEmpty()){
            throw new NotFoundException("Can not found User " + username);
        }else {
            Post post = Post.builder()
                    .user((userFromdb.get()))
                    .body(postBody)
                    .build();
            postRepository.save(post);
            System.out.println("Post created");
        }

    }

    public UserInPost mapUsertoUserInPost(User user){
        return new UserInPost(user.getUsername());
    }

    public PostDto mapPostToPostDto(Post post){
        PostDto postDto = new PostDto();
        postDto.setBody(post.getBody());
        postDto.setId(post.getId());
        postDto.setUserId(post.getUser().getId());
        return postDto;
    }

    public List<PostDto> mapListPostToListDtoToPost(List<Post> posts) {
        List <PostDto> postDtos = new ArrayList<>();
        posts.forEach(p -> postDtos.add(mapPostToPostDto(p)));
        return postDtos;
    }
    public List<PostDto> showPosts(){
        List<PostDto> postDtos = new ArrayList<>();
        postRepository.findAll().forEach(p -> postDtos.add(mapPostToPostDto(p)));
        return postDtos;
    }


}
