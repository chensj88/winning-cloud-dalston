package com.winning.devops.jwt.user.server.exception;

/**
 * @author chensj
 * @date 2019-05-29 13:45
 */
public class UserLoginException extends RuntimeException {
    public UserLoginException(String message) {
        super(message);
    }
}
