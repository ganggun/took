package com.example.springjwt.domain.post.controller;

import com.example.springjwt.domain.post.domain.Category;
import com.example.springjwt.domain.post.dto.request.EditPostRequest;
import com.example.springjwt.domain.post.dto.request.WritePostRequest;
import com.example.springjwt.domain.post.dto.response.PostInfo;
import com.example.springjwt.domain.post.dto.response.PostResponse;
import com.example.springjwt.domain.post.enums.SortType;
import com.example.springjwt.domain.post.service.PostService;
import com.example.springjwt.global.common.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
@Tag(name = "post", description = "게시글")
public class PostController {
    private final PostService postService;

    @Operation(summary = "모든 게시글 가져오기")
    @GetMapping
    public ResponseEntity<BaseResponse<List<PostResponse>>> getPosts(
            @RequestParam(defaultValue = "LATEST") SortType sortType,
            @RequestParam(defaultValue = "0") int page) {
        return BaseResponse.of(postService.getPosts(page, sortType), 200);
    }

    @Operation(summary = "내가 쓴 게시글 가져오기")
    @GetMapping("/me")
    public ResponseEntity<BaseResponse<List<PostResponse>>> getMyPosts(
            @RequestParam(defaultValue = "LATEST") SortType sortType,
            @RequestParam(defaultValue = "0") int page) {
        return BaseResponse.of(postService.getMyPosts(page, sortType), 200);
    }

    @Operation(summary = "게시글 id로 게시글 가져오기")
    @GetMapping("/{postId}")
    public ResponseEntity<BaseResponse<PostInfo>> getPost(@PathVariable Long postId) {
        return BaseResponse.of(postService.getPost(postId), 200);
    }

    @Operation(summary = "게시글 작성")
    @PostMapping("/write")
    public ResponseEntity<BaseResponse<Void>> writePost(
            @RequestParam(defaultValue = "ODOR") Category category,
            @RequestBody WritePostRequest writePostRequest) {
        postService.writePost(category, writePostRequest);

        return BaseResponse.of(null, 200, "success");
    }

    @Operation(summary = "게시글 좋아요")
    @PatchMapping("/{postId}/like")
    public ResponseEntity<BaseResponse<Void>> likePost(@PathVariable Long postId) {
        postService.likePost(postId);

        return BaseResponse.of(null, 200, "success");
    }

    @Operation(summary = "게시글 수정")
    @PatchMapping("/{postId}")
    public ResponseEntity<BaseResponse<Void>> editPost(
            @PathVariable Long postId,
            @RequestParam(defaultValue = "ODOR") Category category,
            @RequestBody EditPostRequest editPostRequest) {
        postService.editPost(postId, category, editPostRequest);

        return BaseResponse.of(null, 200, "success");
    }

    @Operation(summary = "게시글 삭제")
    @DeleteMapping("/{postId}")
    public ResponseEntity<BaseResponse<Void>> deletePost(@PathVariable Long postId) {
        postService.deletePost(postId);

        return BaseResponse.of(null, 200, "success");
    }
}
