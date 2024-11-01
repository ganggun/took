package com.example.springjwt.domain.auth.controller;

import com.example.springjwt.domain.auth.dto.JoinDTO;
import com.example.springjwt.domain.auth.service.JoinService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JoinController {

    private final JoinService joinService;

    public JoinController(JoinService joinService) {

        this.joinService = joinService;
    }

    @PostMapping("/join")
    public String joinProcess(@RequestBody JoinDTO joinDTO) throws Exception{

        joinService.joinProcess(joinDTO);

        return "ok";
    }
}
