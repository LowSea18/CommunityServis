package com.example.Communityservice.Exception;

import lombok.Getter;

@Getter
public class AlreadyExistException extends RuntimeException {
    public AlreadyExistException (final String message){super(message);}
}
