package com.example.Communityservice.tests;

import com.example.Communityservice.Exception.NotFoundException;
import com.example.Communityservice.repository.PostRepository;
import com.example.Communityservice.repository.UserReposiotory;
import com.example.Communityservice.service.PostService;
import com.example.Communityservice.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PostServiceTests {

    @Test
    void shouldNotAddPost_userDoesNotExist_throw_NotFoundEx(){
        var mockUserRepo=mock(UserReposiotory.class);
        when(mockUserRepo.findByusername(anyString())).thenReturn(Optional.empty());
        PostService toTest = new PostService(null,mockUserRepo,null,null);
        assertThrows(NotFoundException.class, () -> {
            toTest.addPostService(anyString(),"Hello");
        });
    }

    @Test
    void shouldNotUpdatePost_PostDoesNotExist_throw_NotFoundEx(){
        var mockPostRepo = mock(PostRepository.class);
        when(mockPostRepo.findById(anyInt())).thenReturn(Optional.empty());
        PostService toTest = new PostService(mockPostRepo,null,null,null);
        assertThrows(NotFoundException.class, () -> {
            toTest.updatePost(anyInt(),"Hello");
        });
    }

    @Test
    void shouldNotDeletePost_PostDoesNotExist_throw_NotFoundEx(){
        var mockPostRepo = mock(PostRepository.class);
        when(mockPostRepo.findById(anyInt())).thenReturn(Optional.empty());
        PostService toTest = new PostService(mockPostRepo,null,null,null);
        assertThrows(NotFoundException.class, () -> {
            toTest.deletePost(anyInt());
        });
    }

}
