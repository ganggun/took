package com.example.springjwt.domain.user.service;

import com.example.springjwt.domain.user.dto.request.EditInfoRequest;
import com.example.springjwt.domain.user.dto.request.EditPasswordRequest;
import com.example.springjwt.domain.user.dto.response.UserResponse;

public interface UserService {
    UserResponse getMe();
    UserResponse editInfo(EditInfoRequest request);
    void editPassword(EditPasswordRequest request);
    void deleteAccount();
}
