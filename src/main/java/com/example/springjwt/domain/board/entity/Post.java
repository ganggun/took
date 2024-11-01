package com.example.springjwt.domain.board.entity;

import com.example.springjwt.domain.comment.entity.Comment;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String content;

    private String userId;

    @OneToMany(mappedBy = "post")
    private List<Comment> comments = new ArrayList<>();

    private Long likes = 0L;
}
