package com.example.user.global;

import lombok.Getter;

@Getter
public class CustomException extends RuntimeException {
    private final int code;

    public CustomException (int code, String message){
        super(message);
        this.code = code;
    }
}
