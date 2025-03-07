package com.example.springjwt.domain.post.dto.response;

import com.example.springjwt.domain.post.domain.Category;

import java.time.LocalDate;

public record PostResponse(
        Long id,
        String title,
        Category category,
        Long likes,
        Boolean isMine,
        LocalDate createdAt
) {
}
