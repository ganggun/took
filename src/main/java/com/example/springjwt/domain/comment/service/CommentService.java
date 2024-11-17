package com.example.springjwt.domain.comment.service;

import com.example.springjwt.domain.comment.dto.response.CommentResponse;

import java.util.List;

public interface CommentService {
    List<CommentResponse> getComments(Long postId);
    List<CommentResponse> getMyComments();
    void writeComment(Long postId, String content);
    void likeComment(Long commentId);
    void editComment(Long commentId, String content);
    void deleteComment(Long commentId);
}
