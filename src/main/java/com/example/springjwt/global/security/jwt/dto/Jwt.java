package com.example.springjwt.global.security.jwt.dto;

public record Jwt(
        String accessToken,
        String refreshToken
) {
}