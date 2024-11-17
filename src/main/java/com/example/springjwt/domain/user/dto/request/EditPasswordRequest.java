package com.example.springjwt.domain.user.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record EditPasswordRequest(
        String oldPassword,
        @NotBlank
        @Size(min = 8, max = 32)
        String newPassword
) {
}
