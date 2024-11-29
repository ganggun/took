package com.example.springjwt.domain.mail.controller;

import com.example.springjwt.domain.mail.service.MailService;
import com.example.springjwt.global.common.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mail")
@RequiredArgsConstructor
public class MailController {
    private final MailService mailService;

    @Operation(summary = "인증코드 받기")
    @GetMapping("/code")
    public ResponseEntity<BaseResponse<Void>> getCode(@RequestParam String email) throws MessagingException {
        mailService.sendMail(email);

        return BaseResponse.of(null, 200, "success");
    }
}
