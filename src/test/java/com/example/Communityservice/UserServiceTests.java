package com.example.Communityservice;

import com.example.Communityservice.Exception.AlreadyExistException;
import com.example.Communityservice.Exception.FailedToLoginException;
import com.example.Communityservice.Exception.NotFoundException;
import com.example.Communityservice.Exception.WrongAgeException;
import com.example.Communityservice.model.CreateUserDto.CreateUserDto;
import com.example.Communityservice.model.LoginRequestDto.LoginRequestDto;
import com.example.Communityservice.model.entityy.User;
import com.example.Communityservice.model.userDto.UpdateUserDto;
import com.example.Communityservice.repository.UserReposiotory;
import com.example.Communityservice.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTests {

    @Test
    void shouldNotFindUserById_userDoesNotExist_throw_NotFoundEx(){
        var mockUserRepo = mock(UserReposiotory.class);
        when(mockUserRepo.findById(anyLong())).thenReturn(Optional.empty());
        UserService toTest = new UserService(mockUserRepo,null,null);
        assertThrows(NotFoundException.class, () -> {
            toTest.getUserByIdService(anyLong());
        });
    }

    @Test
    void shouldNotAddUser_userHaveWrongAge_throw_WrongAgeEx(){
        CreateUserDto createUserDto = new CreateUserDto();
        createUserDto.setAge(17);
        UserService toTest = new UserService(null,null,null);
        assertThrows(WrongAgeException.class, () -> {
            toTest.addUserService(createUserDto);
        });
    }

    @Test
    void shouldNotAddUser_UserAlreadyExist(){
        var mockUserRepo = mock(UserReposiotory.class);
        User user = new User();
        CreateUserDto createUserDto = new CreateUserDto();
        createUserDto.setAge(20);
        createUserDto.setUsername("AnyString");
        when(mockUserRepo.findByusername(anyString())).thenReturn(Optional.of(user));
        UserService toTest = new UserService(mockUserRepo,null,null);
        assertThrows(AlreadyExistException.class, () -> {
            toTest.addUserService(createUserDto);
        });
    }

    @Test
    void shouldNotLogin_badUsername_throw_NotFoundEx(){
        LoginRequestDto loginRequestDto = new LoginRequestDto();
        loginRequestDto.setUsername("AmyString");
        var mockUserRepo = mock(UserReposiotory.class);
        when(mockUserRepo.findByusername(anyString())).thenReturn(Optional.empty());
        UserService toTest = new UserService(mockUserRepo,null,null);
        assertThrows(NotFoundException.class, () -> {
            toTest.loginService(loginRequestDto);
        });
    }

    @Test
    void shouldNotLogin_badPassword_throw_FailedToLoginEx(){
        User user = new User();
        user.setPassword("password");
        LoginRequestDto loginRequestDto = new LoginRequestDto();
        loginRequestDto.setUsername("AnyString");
        loginRequestDto.setPassword("badPassword");
        var mockUserRepo = mock(UserReposiotory.class);
        when(mockUserRepo.findByusername(anyString())).thenReturn(Optional.of(user));
        UserService toTest = new UserService(mockUserRepo,null,null);
        assertThrows(FailedToLoginException.class, () -> {
            toTest.loginService(loginRequestDto);
        });

    }

    @Test
    void shouldNotUpdateUser_wrongAge_throw_WrongAgeEx(){
        UpdateUserDto updateUserDto = new UpdateUserDto();
        updateUserDto.setAge(17);
        UserService toTest = new UserService(null,null,null);
        assertThrows(WrongAgeException.class, () -> {
            toTest.updateUser(1L,updateUserDto);
        });
    }

    @Test
    void shouldNotUpdateUser_userDoesNotExist_throw_NotFoundEx(){
        var mockUserRepo = mock(UserReposiotory.class);
        when(mockUserRepo.findById(anyLong())).thenReturn(Optional.empty());
        UserService toTest = new UserService(mockUserRepo,null,null);
        UpdateUserDto updateUserDto = new UpdateUserDto();
        updateUserDto.setAge(20);
        assertThrows(NotFoundException.class, () -> {
            toTest.updateUser(1L,updateUserDto);
        });
    }

    @Test
    void shouldNotDeleteUser_userDoesNotExist_throw_NotFoundEx(){
        var mockUserRepo = mock(UserReposiotory.class);
        when(mockUserRepo.findById(anyLong())).thenReturn(Optional.empty());
        UserService toTest = new UserService(mockUserRepo,null,null);
        assertThrows(NotFoundException.class, () -> {
            toTest.deleteUser(anyLong());
        });
    }

}
