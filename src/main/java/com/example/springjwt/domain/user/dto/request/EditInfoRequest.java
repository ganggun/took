package com.example.springjwt.domain.user.dto.request;

import jakarta.validation.constraints.NotBlank;

public record EditInfoRequest(
        @NotBlank
        Integer studentNumber,
        @NotBlank
        String name
) {
}
