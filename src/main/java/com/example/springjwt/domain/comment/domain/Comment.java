package com.example.springjwt.domain.comment.domain;

import com.example.springjwt.domain.post.domain.Post;
import com.example.springjwt.domain.user.domain.User;
import com.example.springjwt.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Set;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User writer;

    @ManyToOne(fetch = FetchType.LAZY)
    private Post post;

    @Column(nullable = false)
    private String content;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "likedComments")
    private Set<User> likedUsers;
}
