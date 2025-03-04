package com.example.springjwt.domain.user.controller;

import com.example.springjwt.domain.user.dto.request.EditEmailRequest;
import com.example.springjwt.domain.user.dto.request.EditInfoRequest;
import com.example.springjwt.domain.user.dto.request.EditPasswordRequest;
import com.example.springjwt.domain.user.dto.response.UserResponse;
import com.example.springjwt.domain.user.service.UserService;
import com.example.springjwt.global.common.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Tag(name = "user", description = "회원")
public class UserController {
    private final UserService userService;

    @Operation(summary = "내정보")
    @GetMapping("/me")
    public ResponseEntity<BaseResponse<UserResponse>> getMe() {
        return BaseResponse.of(userService.getMe(), 200);
    }

    @Operation(summary = "회원 정보 수정")
    @PatchMapping
    public ResponseEntity<BaseResponse<Void>> editInfo(@RequestBody EditInfoRequest request) {
        userService.editInfo(request);

        return BaseResponse.of(null, 200);
    }

    @Operation(summary = "이메일 변경")
    @PatchMapping("/email")
    public ResponseEntity<BaseResponse<Void>> changeEmail(@RequestBody EditEmailRequest request) {
        userService.changeEmail(request);

        return BaseResponse.of(null, 200, "success");
    }

    @Operation(summary = "비밀번호 변경")
    @PatchMapping("/password")
    public ResponseEntity<BaseResponse<Void>> editPassword(@RequestBody EditPasswordRequest request) {
        userService.editPassword(request);

        return BaseResponse.of(null, 200, "success");
    }

    @Operation(summary = "계정 삭제")
    @DeleteMapping
    public ResponseEntity<BaseResponse<Void>> deleteAccount() {
        userService.deleteAccount();

        return BaseResponse.of(null, 204, "success");
    }
}