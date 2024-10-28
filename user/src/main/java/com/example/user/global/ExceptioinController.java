package com.example.user.global;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptioinController {
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<Message> customExceptionHandler(CustomException e) {
        return new ResponseEntity<>(Message.builder().code(e.getCode()).status(StatusEnum.of(e.getCode())).message(e.getMessage()).build(), HttpStatusCode.valueOf(e.getCode()));
    }
}
