package com.example.springjwt.domain.comment.mapper;

import com.example.springjwt.domain.comment.domain.Comment;
import com.example.springjwt.domain.comment.dto.response.CommentResponse;
import com.example.springjwt.global.common.Mapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class CommentMapper implements Mapper<Comment, CommentResponse> {

    @Override
    public CommentResponse toResponse(Comment comment) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        return new CommentResponse(
                comment.getId(),
                comment.getPost().getTitle(),
                comment.getWriter().getName(),
                comment.getContent(),
                (long) comment.getLikedUsers().size(),
                comment.getWriter().getEmail().equals(email),
                comment.getCreatedAt().toLocalDate()
        );
    }
}
