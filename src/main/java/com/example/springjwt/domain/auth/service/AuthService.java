package com.example.springjwt.domain.auth.service;

import com.example.springjwt.domain.auth.dto.request.LoginRequest;
import com.example.springjwt.domain.auth.dto.request.ReissueRequest;
import com.example.springjwt.domain.auth.dto.request.JoinRequest;
import com.example.springjwt.global.security.jwt.dto.Jwt;

public interface AuthService {
    void join(JoinRequest request);
    Jwt login(LoginRequest request);
    Jwt reissue(ReissueRequest request);
}
