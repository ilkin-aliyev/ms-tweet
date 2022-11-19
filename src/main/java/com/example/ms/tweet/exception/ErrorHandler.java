package com.example.ms.tweet.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.example.ms.tweet.model.constant.ExceptionConstants.UNEXPECTED_EXCEPTION_CODE;
import static com.example.ms.tweet.model.constant.ExceptionConstants.UNEXPECTED_EXCEPTION_MESSAGE;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Slf4j
@RestControllerAdvice
public class ErrorHandler {
    @ExceptionHandler(Exception.class)
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    public ExceptionResponse handle(Exception ex) {
        log.error("Exception: ", ex);
        return new ExceptionResponse(UNEXPECTED_EXCEPTION_CODE, UNEXPECTED_EXCEPTION_MESSAGE);
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    public ExceptionResponse handle(NotFoundException ex) {
        log.error("NotFoundException: ", ex);
        return new ExceptionResponse(ex.getCode(), ex.getMessage());
    }

    @ExceptionHandler(AuthException.class)
    public ResponseEntity<ExceptionResponse> handle(AuthException ex) {

        log.error("AuthException: ", ex);
        return ResponseEntity
                .status(ex.getHttpStatus())
                .body(new ExceptionResponse(ex.getCode(), ex.getMessage()));
    }
}
