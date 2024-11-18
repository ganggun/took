package com.example.springjwt.domain.auth.dto.request;

public record LoginRequest(
        String email,
        String password
) {
}
