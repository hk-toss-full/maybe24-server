package com.example.user.global;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptioinController {
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(CustomException.class)
    public Message customExceptionHandler(CustomException e) {
        return Message.builder().code(e.getCode()).status(StatusEnum.of(e.getCode())).message(e.getMessage()).build();
    }
}
