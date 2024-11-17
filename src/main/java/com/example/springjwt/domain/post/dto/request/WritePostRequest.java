package com.example.springjwt.domain.post.dto.request;

public record WritePostRequest(
        String title,
        String content
) {
}
