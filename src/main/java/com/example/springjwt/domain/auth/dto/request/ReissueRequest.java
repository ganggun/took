package com.example.springjwt.domain.auth.dto.request;

public record ReissueRequest(
        String refreshToken
) {
}