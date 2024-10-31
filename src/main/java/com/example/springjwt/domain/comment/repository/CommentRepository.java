package com.example.springjwt.domain.comment.repository;

import com.example.springjwt.domain.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
