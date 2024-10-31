package com.example.springjwt.domain.comment.controller;

import com.example.springjwt.domain.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/board/posts/{postId}/comment")
    public String commentPost(@PathVariable Long postId, String commentRequest) {
        return commentService.commentPost(postId, commentRequest);
    }
}
