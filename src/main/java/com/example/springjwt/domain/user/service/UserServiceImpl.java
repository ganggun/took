package com.example.springjwt.domain.user.service;

import com.example.springjwt.domain.auth.error.AuthError;
import com.example.springjwt.domain.auth.repository.RefreshTokenRepository;
import com.example.springjwt.domain.mail.error.MailError;
import com.example.springjwt.domain.mail.repository.MailRepository;
import com.example.springjwt.domain.user.domain.User;
import com.example.springjwt.domain.user.dto.request.EditEmailRequest;
import com.example.springjwt.domain.user.dto.request.EditInfoRequest;
import com.example.springjwt.domain.user.dto.request.EditPasswordRequest;
import com.example.springjwt.domain.user.dto.response.UserResponse;
import com.example.springjwt.domain.user.error.UserError;
import com.example.springjwt.domain.user.repository.UserRepository;
import com.example.springjwt.global.error.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final MailRepository mailRepository;
    private final RefreshTokenRepository refreshTokenRepository;

    @Transactional(readOnly = true)
    @Override
    public UserResponse getMe() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new CustomException(UserError.USER_NOT_FOUND));

        return new UserResponse(
                user.getId(),
                user.getEmail(),
                user.getStudentNumber(),
                user.getName(),
                user.getRole()
        );
    }

    @Transactional
    @Override
    public void editInfo(EditInfoRequest request) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(email).orElseThrow();

        user.setStudentNumber(request.studentNumber());
        user.setName(request.name());

        userRepository.save(user);
    }

    @Transactional
    @Override
    public void changeEmail(EditEmailRequest request) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(email).orElseThrow();

        if (userRepository.existsByEmail(request.email())) {
            throw new CustomException(UserError.EMAIL_DUPLICATION);
        }

        if (!mailRepository.existsByEmail(request.email()) || !mailRepository.findByEmail(request.email()).equals(request.authCode())) {
            throw new CustomException(MailError.AUTHENTICODE_INVALID);
        }

        user.setEmail(request.email());
        userRepository.save(user);
        mailRepository.deleteByEmail(request.email());
    }

    @Transactional
    @Override
    public void editPassword(EditPasswordRequest request) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(email).orElseThrow();

        String oldPassword = request.oldPassword();
        String newPassword = request.newPassword();

        if (!passwordEncoder.matches(oldPassword, user.getPassword()))
            throw new CustomException(AuthError.WRONG_PASSWORD);

        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }

    @Transactional
    @Override
    public void deleteAccount() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(email).orElseThrow();
        refreshTokenRepository.deleteByEmail(email);
        userRepository.delete(user);
    }
}
