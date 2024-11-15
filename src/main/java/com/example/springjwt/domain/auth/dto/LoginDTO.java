package com.example.springjwt.domain.auth.dto;

import lombok.Data;

@Data
public class LoginDTO {

    private String username;
    private String password;
}
