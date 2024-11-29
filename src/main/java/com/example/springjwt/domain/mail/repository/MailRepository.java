package com.example.springjwt.domain.mail.repository;

public interface MailRepository {
    void save(String email, String authCode);
    String findByEmail(String email);
    void deleteByEmail(String email);
    Boolean existsByEmail(String email);
}
