package com.example.springjwt.domain.auth.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class JoinDTO {

    private String id;
    private String username;
    private String password;
    public String studentNumber;

}
