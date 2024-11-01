package com.example.springjwt.domain.auth.repository;

import com.example.springjwt.domain.auth.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, String> {

    Boolean existsByUsername(String username);

    UserEntity findByUsername(String username);
}
