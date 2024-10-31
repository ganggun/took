package com.example.springjwt.domain.comment.service;

import com.example.springjwt.domain.auth.dto.CustomUserDetails;
import com.example.springjwt.domain.board.entity.Post;
import com.example.springjwt.domain.board.repository.BoardRepository;
import com.example.springjwt.domain.comment.entity.Comment;
import com.example.springjwt.domain.comment.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;

    public String commentPost(Long postId, String commentRequest) {
        Post post = boardRepository.findById(postId).orElseThrow();
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        CustomUserDetails userDetails = (CustomUserDetails) principal;
        Comment comment = new Comment();
        comment.setUserId(userDetails.getUserEntity().getId());
        comment.setContent(commentRequest);
        post.getComments().add(comment);
        boardRepository.save(post);

        return "success";
    }

}
