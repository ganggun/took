package com.example.springjwt.domain.board.service;

import com.example.springjwt.domain.auth.dto.CustomUserDetails;
import com.example.springjwt.domain.auth.entity.UserEntity;
import com.example.springjwt.domain.auth.repository.UserRepository;
import com.example.springjwt.domain.board.dto.WritePostRequest;
import com.example.springjwt.domain.board.entity.Post;
import com.example.springjwt.domain.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    public List<Post> getAllPosts() {
        return boardRepository.findAll();
    }

    public String writePost(WritePostRequest writePostRequest) {
        Post post = new Post();
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        CustomUserDetails userDetails = (CustomUserDetails) principal;
        post.setTitle(writePostRequest.title());
        post.setContent(writePostRequest.content());
        post.setWriter(userDetails.getUserEntity());
        boardRepository.save(post);

        return "success";
    }

    public String likePost(Long postId) {
        Post post = boardRepository.findById(postId).orElseThrow();
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        CustomUserDetails userDetails = (CustomUserDetails) principal;
        UserEntity user = userDetails.getUserEntity();
        user.getLikedPosts().add(postId);
        userRepository.save(user);
        post.setLikes(post.getLikes() + 1);
        boardRepository.save(post);

        return "success";
    }
}
