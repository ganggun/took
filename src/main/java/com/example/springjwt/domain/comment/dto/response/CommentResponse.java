package com.example.springjwt.domain.comment.dto.response;

import java.time.LocalDate;

public record CommentResponse(
        Long id,
        String postTitle,
        String writer,
        String content,
        Long likes,
        Boolean isMine,
        LocalDate createdAt
) {
}
