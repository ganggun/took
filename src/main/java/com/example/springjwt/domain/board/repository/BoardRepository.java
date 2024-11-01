package com.example.springjwt.domain.board.repository;

import com.example.springjwt.domain.board.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Post, Long> {
}
