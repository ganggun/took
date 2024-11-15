package com.example.springjwt.domain.auth.service;

import com.example.springjwt.domain.auth.dto.CustomUserDetails;
import com.example.springjwt.domain.auth.entity.UserEntity;
import com.example.springjwt.domain.auth.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {

        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {

        UserEntity userData = userRepository.findByUsername(username);
        return new CustomUserDetails(userData);
    }
}
