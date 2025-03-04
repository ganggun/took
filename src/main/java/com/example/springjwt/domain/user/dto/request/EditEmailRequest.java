package com.example.springjwt.domain.user.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record EditEmailRequest(
        @NotBlank
        String authCode,
        @NotBlank
        @Email
        String email
) {
}
