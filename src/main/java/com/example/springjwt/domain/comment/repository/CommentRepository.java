package com.example.springjwt.domain.comment.repository;

import com.example.springjwt.domain.comment.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByPostId(Long postId);
    List<Comment> findAllByWriterEmail(String writerEmail);
}
