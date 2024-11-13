package com.example.springjwt.domain.comment.controller;

import com.example.springjwt.domain.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/boards/{postId}/comments")
    public String commentPost(@PathVariable("postId") Long postId,
                              @RequestParam String comment
    ) {
        return commentService.commentPost(postId, comment);
    }
}
