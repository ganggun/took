package com.example.springjwt.domain.board.service;

import com.example.springjwt.domain.board.dto.WritePostRequest;
import com.example.springjwt.domain.board.entity.Post;
import com.example.springjwt.domain.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    public List<Post> getAllPosts() {
        return boardRepository.findAll();
    }

    public String writePost(WritePostRequest writePostRequest) {
        Post post = new Post();
        post.setTitle(writePostRequest.title());
        post.setWriter(writePostRequest.writer());
        post.setContent(writePostRequest.content());
        boardRepository.save(post);

        return "success";
    }

    public String likePost(Long postId) {
        Post post = boardRepository.findById(postId).orElseThrow();
        post.setLike(post.getLike() + 1);
        boardRepository.save(post);

        return "success";
    }

    public String commentPost(Long postId, ) {
        Post post = boardRepository.findById(postId).orElseThrow();
        post.setComments(post.getComments().add(comment));
    }
}
