package com.example.springjwt.domain.user.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record EditInfoRequest(
        @NotBlank
        @Email
        String email,
        @NotBlank
        Integer studentNumber,
        @NotBlank
        String name
) {
}
