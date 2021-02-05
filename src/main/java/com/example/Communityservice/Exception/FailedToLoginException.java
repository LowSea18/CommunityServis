package com.example.Communityservice.Exception;

import lombok.Getter;

@Getter
public class FailedToLoginException extends RuntimeException{
    public FailedToLoginException(final String message){super(message);}
}
