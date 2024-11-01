package com.example.springjwt.domain.comment.entity;

import com.example.springjwt.domain.board.entity.Post;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userId;

    private String content;

    @ManyToOne
    @JoinColumn
    private Post post;

    private Long likes = 0L;
}
