package com.example.springjwt.domain.user.domain;

import com.example.springjwt.domain.post.domain.Post;
import com.example.springjwt.domain.comment.domain.Comment;
import com.example.springjwt.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SuperBuilder
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private Integer studentNumber;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String password;

    @OneToMany(mappedBy = "writer", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    @Builder.Default
    private Set<Post> posts = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @Builder.Default
    private Set<Post> likedPosts = new HashSet<>();

    @OneToMany(mappedBy = "writer", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    @Builder.Default
    private Set<Comment> comments = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @Builder.Default
    private Set<Comment> likedComments = new HashSet<>();

    @Enumerated(EnumType.STRING)
    private UserRole role;
}
