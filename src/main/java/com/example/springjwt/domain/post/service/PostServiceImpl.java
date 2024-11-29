package com.example.springjwt.domain.post.service;

import com.example.springjwt.domain.comment.domain.Comment;
import com.example.springjwt.domain.comment.mapper.CommentMapper;
import com.example.springjwt.domain.post.domain.Category;
import com.example.springjwt.domain.post.domain.Post;
import com.example.springjwt.domain.post.dto.request.EditPostRequest;
import com.example.springjwt.domain.post.dto.request.WritePostRequest;
import com.example.springjwt.domain.post.dto.response.PostInfo;
import com.example.springjwt.domain.post.dto.response.PostResponse;
import com.example.springjwt.domain.post.enums.SortType;
import com.example.springjwt.domain.post.error.PostError;
import com.example.springjwt.domain.post.mapper.PostMapper;
import com.example.springjwt.domain.post.repository.PostRepository;
import com.example.springjwt.domain.user.domain.User;
import com.example.springjwt.domain.user.repository.UserRepository;
import com.example.springjwt.global.error.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final PostMapper postMapper;
    private final CommentMapper commentMapper;

    @Override
    public List<PostResponse> getPosts(int page, SortType sortType) {
        Pageable pageable = PageRequest.of(page, 20);
        Page<Post> posts;

        switch (sortType) {
            case LATEST -> posts = postRepository.findAllSortedByDate(pageable);

            case MOST_LIKED -> posts = postRepository.findAllSortedByLikes(pageable);

            case MOST_COMMENTED -> posts = postRepository.findAllSortedByComments(pageable);

            default -> throw new IllegalArgumentException("Unexpected value: " + sortType);
        }

        return posts.stream().map(postMapper::toResponse).toList();
    }

    @Override
    public List<PostResponse> getMyPosts(int page, SortType sortType) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Pageable pageable = PageRequest.of(page, 20, Sort.by(Sort.Direction.DESC, "createdAt"));
        Page<Post> posts = postRepository.findAllByWriterEmail(email, pageable);

        return posts.stream().map(postMapper::toResponse).toList();
    }

    @Override
    public PostInfo getPost(Long postId) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new CustomException(PostError.POST_NOT_FOUND));

        return new PostInfo(
                post.getId(),
                post.getTitle(),
                post.getCategory(),
                post.getComments().stream().map(commentMapper::toResponse).toList(),
                (long) post.getLikedUsers().size(),
                post.getWriter().getEmail().equals(email),
                post.getCreatedAt().toLocalDate()
        );
    }

    @Override
    public void writePost(Category category, WritePostRequest writePostRequest) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(email).orElseThrow();
        Post post = Post.builder()
                .title(writePostRequest.title())
                .content(writePostRequest.content())
                .writer(user)
                .category(category)
                .build();

        postRepository.save(post);
    }

    @Override
    public void likePost(Long postId) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(email).orElseThrow();
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new CustomException(PostError.POST_NOT_FOUND));

        if (user.getLikedPosts().contains(post)) {
            post.getLikedUsers().remove(user);
            user.getLikedPosts().remove(post);
        } else {
            post.getLikedUsers().add(user);
            user.getLikedPosts().add(post);
        }

        postRepository.save(post);
        userRepository.save(user);
    }

    @Override
    public void editPost(Long postId, Category category, EditPostRequest editPostRequest) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new CustomException(PostError.POST_NOT_FOUND));

        if (!post.getWriter().getEmail().equals(email)) {
            throw new CustomException(PostError.CANNOT_EDIT_POST);
        }

        if (!editPostRequest.title().isEmpty()) {
            post.setTitle(editPostRequest.title());
        } else {
            throw new CustomException(PostError.EMPTY_TITLE);
        }

        if (!editPostRequest.content().isEmpty()) {
            post.setContent(editPostRequest.content());
        } else {
            throw new CustomException(PostError.EMPTY_CONTENT);
        }

        post.setCategory(category);

        postRepository.save(post);
    }

    @Override
    public void deletePost(Long postId) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new CustomException(PostError.POST_NOT_FOUND));

        if (post.getWriter().getEmail().equals(email)) {
            post.getLikedUsers().forEach(user -> user.getLikedPosts().remove(post));
            post.getComments().forEach(comment -> comment.getWriter().getLikedComments().remove(comment));
            postRepository.delete(post);
        } else {
            throw new CustomException(PostError.CANNOT_DELETE_POST);
        }
    }
}
