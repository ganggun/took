package com.example.springjwt.domain.post.dto.response;

import com.example.springjwt.domain.comment.dto.response.CommentResponse;
import com.example.springjwt.domain.post.domain.Category;

import java.time.LocalDate;
import java.util.List;

public record PostResponse(
        Long id,
        String title,
        Category category,
        Long likes,
        List<CommentResponse> comments,
        Boolean isMine,
        LocalDate createdAt
) {
}
