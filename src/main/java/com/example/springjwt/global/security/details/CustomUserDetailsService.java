package com.example.springjwt.global.security.details;

import com.example.springjwt.domain.user.domain.User;
import com.example.springjwt.domain.user.error.UserError;
import com.example.springjwt.domain.user.repository.UserRepository;
import com.example.springjwt.global.error.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new CustomException(UserError.USER_NOT_FOUND));

        return new CustomUserDetails(user);
    }
}