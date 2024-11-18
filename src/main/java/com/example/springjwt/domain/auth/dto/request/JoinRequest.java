package com.example.springjwt.domain.auth.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record JoinRequest(
        @NotBlank
        @Email
        String email,
        @NotBlank
        String username,
        @NotBlank
        @Size(min = 8, max = 32)
        String password,
        @NotBlank
        Integer studentNumber
) {
}