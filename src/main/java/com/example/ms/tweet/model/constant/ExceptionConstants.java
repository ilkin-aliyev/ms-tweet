package com.example.ms.tweet.model.constant;

public interface ExceptionConstants {
    String UNEXPECTED_EXCEPTION_CODE = "UNEXPECTED_EXCEPTION";
    String UNEXPECTED_EXCEPTION_MESSAGE = "Unexpected exception occurred";

    String POST_NOT_FOUND_CODE = "POST_NOT_FOUND";
    String POST_NOT_FOUND_MESSAGE = "Post with id:%s not found";

    String COMMENT_NOT_FOUND_CODE = "COMMENT_NOT_FOUND";
    String COMMENT_NOT_FOUND_MESSAGE = "Comment with id:%s not found";

    String TOKEN_EXPIRED_CODE = "TOKEN_EXPIRED";
    String TOKEN_EXPIRED_MESSAGE = "Token expired";
    String USER_UNAUTHORIZED_CODE = "USER_UNAUTHORIZED";
    String USER_UNAUTHORIZED_MESSAGE = "User unauthorized";
    String REFRESH_TOKEN_EXPIRED_MESSAGE = "Refresh token expired";
    String REFRESH_TOKEN_COUNT_EXPIRED_MESSAGE = "Refresh token count expired";
}
