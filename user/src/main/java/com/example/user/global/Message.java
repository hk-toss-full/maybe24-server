package com.example.user.global;

import lombok.Builder;

@Builder
public record Message(StatusEnum status, int code, String message, Object data) {
}