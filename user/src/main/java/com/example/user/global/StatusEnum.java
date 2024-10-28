package com.example.user.global;

import java.util.Arrays;

public enum StatusEnum {
    OK(200, "OK"),
    BAD_REQUEST(400, "BAD_REQUEST"),
    UNAUTHORIZED(401, "UNAUTHORIZED"),
    NOT_FOUND(404, "NOT_FOUND"),
    CONFLICT(409, "CONFLICT"),
    INTERNAL_SERVER_ERROR(500, "INTERNAL_SERVER_ERROR");

    String status;
    int code;

    StatusEnum(int code, String status ) {
        this.code = code;
        this.status = status;
    }

    public static StatusEnum of(int code) {
        return Arrays.stream(StatusEnum.values())
                .filter(i->i.code == code)
                .findAny()
                .orElse(null);
    }
}