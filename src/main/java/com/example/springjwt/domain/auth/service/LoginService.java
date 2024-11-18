package com.example.springjwt.domain.auth.service;

import com.example.springjwt.domain.auth.dto.LoginDTO;
import com.example.springjwt.domain.auth.entity.UserEntity;
import com.example.springjwt.domain.auth.jwt.JWTUtil;
import com.example.springjwt.domain.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountNotFoundException;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final UserRepository userRepository;
    private final JWTUtil jwtUtil;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public String login(LoginDTO loginDTO) throws AccountNotFoundException {
        UserEntity user = userRepository.findByUsername(loginDTO.getUsername());
        if (bCryptPasswordEncoder.matches(loginDTO.getPassword(), user.getPassword())) {
            return jwtUtil.createJwt("access", user.getUsername(), user.getRole(), 36000L);
        } else {
            return "user does not match";
        }
    }
}
