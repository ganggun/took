package com.example.springjwt.domain.comment.service;

import com.example.springjwt.domain.comment.dto.response.CommentResponse;
import com.example.springjwt.domain.comment.error.CommentError;
import com.example.springjwt.domain.comment.mapper.CommentMapper;
import com.example.springjwt.domain.post.domain.Post;
import com.example.springjwt.domain.post.repository.PostRepository;
import com.example.springjwt.domain.comment.domain.Comment;
import com.example.springjwt.domain.comment.repository.CommentRepository;
import com.example.springjwt.domain.user.domain.User;
import com.example.springjwt.domain.user.error.UserError;
import com.example.springjwt.domain.user.repository.UserRepository;
import com.example.springjwt.global.error.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final CommentMapper commentMapper;

    @Transactional(readOnly = true)
    @Override
    public List<CommentResponse> getComments(Long postId) {
        List<Comment> comments = commentRepository.findAllByPostId(postId);

        return comments.stream().map(commentMapper::toResponse).toList();
    }

    @Transactional(readOnly = true)
    @Override
    public List<CommentResponse> getMyComments() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        List<Comment> comments = commentRepository.findAllByWriterEmail(email);

        return comments.stream().map(commentMapper::toResponse).toList();
    }

    @Transactional
    @Override
    public void writeComment(Long postId, String content) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(email).orElseThrow();
        Post post = postRepository.findById(postId).orElseThrow();
        Comment comment = Comment.builder()
                .writer(user)
                .post(post)
                .content(content)
                .build();

        post.getComments().add(comment);

        commentRepository.save(comment);
        postRepository.save(post);
    }

    @Transactional
    @Override
    public void likeComment(Long commentId) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new CustomException(UserError.USER_NOT_FOUND));
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new CustomException(CommentError.COMMENT_NOT_FOUND));

        if (user.getLikedComments().contains(comment)) {
            comment.getLikedUsers().remove(user);
            user.getLikedComments().remove(comment);
        } else {
            comment.getLikedUsers().add(user);
            user.getLikedComments().add(comment);
        }

        commentRepository.save(comment);
        userRepository.save(user);
    }

    @Transactional
    @Override
    public void editComment(Long commentId, String content) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new CustomException(CommentError.COMMENT_NOT_FOUND));

        if (!Objects.equals(comment.getWriter().getEmail(), email)) {
            throw new CustomException(CommentError.COMMENT_NOT_FOUND);
        }

        if (!content.isEmpty()) {
            comment.setContent(content);
        } else {
            throw new CustomException(CommentError.EMPTY_CONTENT);
        }

        commentRepository.save(comment);
    }

    @Transactional
    @Override
    public void deleteComment(Long commentId) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new CustomException(CommentError.COMMENT_NOT_FOUND));

        if (comment.getWriter().getEmail().equals(email)) {
            comment.getLikedUsers().forEach(it -> it.getLikedComments().remove(comment));
            comment.getWriter().getComments().remove(comment);
            comment.getPost().getComments().remove(comment);
            commentRepository.delete(comment);
        } else {
            throw new CustomException(CommentError.CANNOT_DELETE_COMMENT);
        }
    }
}
