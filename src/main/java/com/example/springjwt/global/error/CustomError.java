package com.example.springjwt.global.error;

public interface CustomError {
    String getMessage();

    int getStatus();

    String getCode();
}
