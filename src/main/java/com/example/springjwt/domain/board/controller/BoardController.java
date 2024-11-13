package com.example.springjwt.domain.board.controller;

import com.example.springjwt.domain.board.dto.WritePostRequest;
import com.example.springjwt.domain.board.entity.Post;
import com.example.springjwt.domain.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/boards")
public class BoardController {
    private final BoardService boardService;

    @GetMapping("")
    public List<Post> getAllPosts() {
        return boardService.getAllPosts();
    }

    @PostMapping("/write")
    public String writePost(@RequestBody WritePostRequest writePostRequest) {
        return boardService.writePost(writePostRequest);
    }

    @PostMapping("/posts/{postId}/like")
    public String likePost(@PathVariable Long postId) {
        return boardService.likePost(postId);
    }
}
