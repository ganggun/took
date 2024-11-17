package com.example.springjwt.domain.auth.controller;

import com.example.springjwt.domain.auth.dto.request.LoginRequest;
import com.example.springjwt.domain.auth.dto.request.ReissueRequest;
import com.example.springjwt.domain.auth.dto.request.JoinRequest;
import com.example.springjwt.domain.auth.service.AuthService;
import com.example.springjwt.global.common.BaseResponse;
import com.example.springjwt.global.security.jwt.dto.Jwt;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
@Tag(name = "auth", description = "인증")
public class AuthController {
    private final AuthService authService;

    @Operation(summary = "회원가입")
    @PostMapping("/join")
    public ResponseEntity<BaseResponse<Void>> join(@RequestBody JoinRequest request) {
        authService.join(request);

        return BaseResponse.of(null, 201, "success");
    }

    @Operation(summary = "로그인")
    @PostMapping("/login")
    public ResponseEntity<BaseResponse<Jwt>> login(@RequestBody LoginRequest request) {
        return BaseResponse.of(authService.login(request), 201, "success");
    }

    @Operation(summary = "토큰 재발급")
    @PostMapping("/reissue")
    public ResponseEntity<BaseResponse<Jwt>> reissue(@RequestBody ReissueRequest request) {
        return BaseResponse.of(authService.reissue(request), 201, "success");
    }
}
