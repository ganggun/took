package com.example.springjwt.domain.board.controller;

import com.example.springjwt.domain.board.entity.Post;
import com.example.springjwt.domain.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    public List<Post> getAllPosts() {
        return boardService.getAllPosts();
    }

    public String writePost(@RequestBody ) {}
}
