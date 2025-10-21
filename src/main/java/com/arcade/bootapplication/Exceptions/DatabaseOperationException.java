package com.arcade.bootapplication.Exceptions;

public class DatabaseOperationException extends RuntimeException {
    public DatabaseOperationException(String message, Exception e) {
        super(message);
    }
}
