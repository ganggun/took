package com.example.springjwt.domain.board.dto;

import lombok.Data;

@Data
public class WritePostRequest {

    private final String title;

    private final String content;
}
