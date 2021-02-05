package com.example.Communityservice.Exception;

import lombok.Getter;

@Getter
public class WrongAgeException extends RuntimeException {
    public WrongAgeException(final String message){super(message);}
}
