package com.example.Communityservice.Exception;

import lombok.Getter;

@Getter
public class NotFoundException extends  RuntimeException {
    public NotFoundException (final String message){super(message);}
}