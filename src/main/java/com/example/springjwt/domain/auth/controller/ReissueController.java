package com.example.springjwt.domain.auth.controller;
import com.example.springjwt.domain.auth.service.ReissueService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ReissueController {
    private final ReissueService reissueService;

    @PostMapping("/reissue")
    public void reissue(HttpServletRequest request, HttpServletResponse response) {
         reissueService.reissue(request,response);
    }


}
