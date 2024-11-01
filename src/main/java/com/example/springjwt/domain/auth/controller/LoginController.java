package com.example.springjwt.domain.auth.controller;

import com.example.springjwt.domain.auth.dto.LoginDTO;
import com.example.springjwt.domain.auth.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.security.auth.login.AccountNotFoundException;

@RestController
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @PostMapping("/login")
    public String login(@RequestBody LoginDTO loginDTO) throws AccountNotFoundException {
        return loginService.login(loginDTO);
    }
}
