package com.example.springjwt.domain.mail.error;

import com.example.springjwt.global.error.CustomError;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MailError implements CustomError {
    EMAIL_INVALID(400, "Email invalid"),
    AUTHENTICODE_INVALID(400, "Authenticate code invalid"),
    ;

    private final int status;
    private final String message;

    @Override
    public String getCode() {
        return name();
    }
}
