package com.example.springjwt.domain.user.dto.response;

import com.example.springjwt.domain.user.domain.UserRole;

public record UserResponse(
        Long id,
        String email,
        Integer studentNumber,
        String name,
        UserRole role
) {
}
