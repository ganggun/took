package com.example.springjwt.domain.auth.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Setter
@Getter
public class UserEntity {

    @Id
    private String id;

    private String username;
    private String studentNumber;
    private String password;

    private String role;

    @ElementCollection()
    private Set<Long> likedPosts = new LinkedHashSet<>();

    @ElementCollection
    private Set<Long> likedComments = new LinkedHashSet<>();
}
