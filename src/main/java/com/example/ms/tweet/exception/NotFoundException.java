package com.example.ms.tweet.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class NotFoundException extends RuntimeException {
    private String code;

    public NotFoundException(String message, String code) {
        super(message);
        this.code = code;
    }
}
