package com.example.springjwt.domain.post.mapper;

import com.example.springjwt.domain.comment.mapper.CommentMapper;
import com.example.springjwt.domain.post.domain.Post;
import com.example.springjwt.domain.post.dto.response.PostResponse;
import com.example.springjwt.global.common.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PostMapper implements Mapper<Post, PostResponse> {

    private final CommentMapper commentMapper;

    @Override
    public PostResponse toResponse(Post post) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        return new PostResponse(
                post.getId(),
                post.getTitle(),
                post.getCategory(),
                (long) post.getLikedUsers().size(),
                post.getComments().stream().map(commentMapper::toResponse).toList(),
                post.getWriter().getEmail().equals(email),
                post.getCreatedAt().toLocalDate()
        );
    }
}
