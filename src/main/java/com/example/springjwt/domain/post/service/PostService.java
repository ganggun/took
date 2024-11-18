package com.example.springjwt.domain.post.service;

import com.example.springjwt.domain.post.domain.Category;
import com.example.springjwt.domain.post.dto.request.EditPostRequest;
import com.example.springjwt.domain.post.dto.request.WritePostRequest;
import com.example.springjwt.domain.post.dto.response.PostResponse;
import com.example.springjwt.domain.post.enums.SortType;

import java.util.List;

public interface PostService {
    List<PostResponse> getPosts(int page, SortType sortType);
    List<PostResponse> getMyPosts(int page, SortType sortType);
    PostResponse getPost(Long postId);
    void writePost(Category category, WritePostRequest writePostRequest);
    void likePost(Long postId);
    void editPost(Long postId, Category category, EditPostRequest editPostRequest);
    void deletePost(Long postId);
}
