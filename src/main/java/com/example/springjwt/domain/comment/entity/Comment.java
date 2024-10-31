package com.example.springjwt.domain.comment.entity;

import lombok.Data;

@Data
public class Comment {

    private Long id;

    private Long userId;

    private String content;

    private Long like;
}
