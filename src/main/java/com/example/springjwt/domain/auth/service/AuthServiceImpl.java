package com.example.springjwt.domain.auth.service;

import com.example.springjwt.domain.auth.dto.request.LoginRequest;
import com.example.springjwt.domain.auth.dto.request.ReissueRequest;
import com.example.springjwt.domain.auth.dto.request.JoinRequest;
import com.example.springjwt.domain.auth.error.AuthError;
import com.example.springjwt.domain.auth.repository.RefreshTokenRepository;
import com.example.springjwt.domain.user.domain.User;
import com.example.springjwt.domain.user.domain.UserRole;
import com.example.springjwt.domain.user.error.UserError;
import com.example.springjwt.domain.user.repository.UserRepository;
import com.example.springjwt.global.error.CustomException;
import com.example.springjwt.global.security.jwt.dto.Jwt;
import com.example.springjwt.global.security.jwt.enums.JwtType;
import com.example.springjwt.global.security.jwt.error.JwtError;
import com.example.springjwt.global.security.jwt.provider.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final RefreshTokenRepository refreshTokenRepository;

    @Transactional
    @Override
    public void join(JoinRequest request) {
        String email = request.email();
        String password = request.password();
        Integer studentNumber = request.studentNumber();
        String name = request.username();

        if (userRepository.existsByEmail(email)) throw new CustomException(UserError.EMAIL_DUPLICATION);

        User user = User.builder()
                .email(email)
                .name(name)
                .password(passwordEncoder.encode(password))
                .studentNumber(studentNumber)
                .role(UserRole.USER)
                .build();

        userRepository.save(user);
    }

    @Transactional(readOnly = true)
    @Override
    public Jwt login(LoginRequest request) {
        String email = request.email();
        String password = request.password();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new CustomException(UserError.USER_NOT_FOUND));

        if (!passwordEncoder.matches(password, user.getPassword()))
            throw new CustomException(AuthError.WRONG_PASSWORD);

        return jwtProvider.generateToken(email);
    }

    @Override
    public Jwt reissue(ReissueRequest request) {
        String refreshToken = request.refreshToken();

        if (jwtProvider.getType(refreshToken) != JwtType.REFRESH)
            throw new CustomException(JwtError.INVALID_TOKEN_TYPE);

        String email = jwtProvider.getSubject(refreshToken);

        if (!refreshTokenRepository.existsByEmail(email))
            throw new CustomException(JwtError.INVALID_REFRESH_TOKEN);

        if (!refreshTokenRepository.findByEmail(email).equals(refreshToken))
            throw new CustomException(JwtError.INVALID_REFRESH_TOKEN);

        if (!userRepository.existsByEmail(email)) throw new CustomException(UserError.USER_NOT_FOUND);

        return jwtProvider.generateToken(email);
    }
}
