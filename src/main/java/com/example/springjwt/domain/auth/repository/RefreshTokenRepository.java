package com.example.springjwt.domain.auth.repository;

public interface RefreshTokenRepository {
    void save(String email, String refreshToken);

    String findByEmail(String email);

    void deleteByEmail(String email);

    Boolean existsByEmail(String email);
}
