package com.example.springjwt.domain.mail.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;

@Repository
@RequiredArgsConstructor
public class MailRepositoryImpl implements MailRepository {
    private final RedisTemplate<String, String> redisTemplate;

    @Override
    public void save(String email, String authCode) {
        redisTemplate.opsForValue().set("authCode:" + email, authCode, 180, TimeUnit.SECONDS);
    }

    @Override
    public String findByEmail(String email) {
        return redisTemplate.opsForValue().get("authCode:" + email);
    }

    @Override
    public void deleteByEmail(String email) {
        redisTemplate.delete("authCode:" + email);
    }

    @Override
    public Boolean existsByEmail(String email) {
        return redisTemplate.hasKey("authCode:" + email);
    }
}
