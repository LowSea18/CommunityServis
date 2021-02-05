package com.example.Communityservice.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;

@ControllerAdvice
public class Exception {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    @ResponseBody
    public ErrorMessage  handleNotFoundException(final NotFoundException e){
        return ErrorMessage.builder()
                .message(e.getMessage())
                .time(LocalDateTime.now())
                .build();
    }
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(FailedToLoginException.class)
    @ResponseBody
    public ErrorMessage  handleNotUnException(final FailedToLoginException e){
        return ErrorMessage.builder()
                .message(e.getMessage())
                .time(LocalDateTime.now())
                .build();
    }
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    @ExceptionHandler(AlreadyExistException.class)
    @ResponseBody
    public ErrorMessage  handleNotUnException(final AlreadyExistException e){
        return ErrorMessage.builder()
                .message(e.getMessage())
                .time(LocalDateTime.now())
                .build();
    }
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    @ExceptionHandler(WrongAgeException.class)
    @ResponseBody
    public ErrorMessage  handleNotUnException(final WrongAgeException e){
        return ErrorMessage.builder()
                .message(e.getMessage())
                .time(LocalDateTime.now())
                .build();
    }
}
