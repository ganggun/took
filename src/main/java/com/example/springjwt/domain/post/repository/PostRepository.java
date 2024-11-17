package com.example.springjwt.domain.post.repository;

import com.example.springjwt.domain.post.domain.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PostRepository extends JpaRepository<Post, Long> {
    @Query("SELECT p FROM Post p ORDER BY p.createdAt DESC")
    Page<Post> findAllSortedByDate(Pageable pageable);
    @Query("SELECT p FROM Post p ORDER BY SIZE(p.likedUsers) DESC")
    Page<Post> findAllSortedByLikes(Pageable pageable);
    @Query("SELECT p FROM Post p ORDER BY SIZE(p.comments) DESC")
    Page<Post> findAllSortedByComments(Pageable pageable);
    Page<Post> findAllByWriterEmail(String email, Pageable pageable);
}
