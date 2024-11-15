package com.example.springjwt.domain.auth.dto;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@ToString
public class JoinDTO {

    private String username;
    private String password;
    private String StudentNumber;

}