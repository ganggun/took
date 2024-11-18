package com.example.springjwt.domain.user.error;

import com.example.springjwt.global.error.CustomError;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserError implements CustomError {
    USER_NOT_FOUND(404, "User not found"),
    EMAIL_DUPLICATION(400, "Email is already in use"),
    ;

    private final int status;
    private final String message;

    @Override
    public String getCode() {
        return name();
    }
}