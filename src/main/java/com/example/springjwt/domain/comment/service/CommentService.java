package com.example.springjwt.domain.comment.service;

import com.example.springjwt.domain.board.entity.Post;
import com.example.springjwt.domain.board.repository.BoardRepository;
import com.example.springjwt.domain.comment.entity.Comment;
import com.example.springjwt.domain.comment.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;

    public String commentPost(Long postId, String commentRequest) {
        Post post = boardRepository.findById(postId).orElseThrow();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        Comment comment = new Comment();
        comment.setUserId(user);
        comment.setContent(commentRequest);
        List<Comment> comments = post.getComments();
        comments.add(comment);
        post.setComments(comments);
        boardRepository.save(post);

        return "success";
    }

}
