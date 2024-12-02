package com.example.springjwt.domain.user.controller;

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
    @PatchMapping("/edit")
    public ResponseEntity<BaseResponse<UserResponse>> editInfo(@RequestBody EditInfoRequest request) {
        return BaseResponse.of(userService.editInfo(request), 200);
    }

    @Operation(summary = "비밀번호 변경")
    @PatchMapping("/edit/password")
    public ResponseEntity<BaseResponse<Void>> editPassword(@RequestBody EditPasswordRequest request) {
        userService.editPassword(request);

        return BaseResponse.of(null, 200, "success");
    }

    @Operation(summary = "계정 삭제")
    @DeleteMapping("/delete")
    public ResponseEntity<BaseResponse<Void>> deleteAccount() {
        userService.deleteAccount();

        return BaseResponse.of(null, 204, "success");
    }
}