package com.example.springjwt.domain.board.service;

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
}
