package com.example.springjwt.domain.comment.controller;

import com.example.springjwt.domain.comment.dto.response.CommentResponse;
import com.example.springjwt.domain.comment.service.CommentService;
import com.example.springjwt.global.common.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comments")
@Tag(name = "comment", description = "댓글")
public class CommentController {
    private final CommentService commentService;

    @Operation(summary = "댓글 보기")
    @GetMapping("/{postId}")
    public ResponseEntity<BaseResponse<List<CommentResponse>>> getComments(@PathVariable Long postId) {
        return BaseResponse.of(commentService.getComments(postId), 200);
    }

    @Operation(summary = "내가 쓴 댓글")
    @GetMapping("/me")
    public ResponseEntity<BaseResponse<List<CommentResponse>>> getCommentsByUserId() {
        return BaseResponse.of(commentService.getMyComments(), 200);
    }

    @Operation(summary = "댓글 작성")
    @PostMapping("/{postId}")
    public ResponseEntity<BaseResponse<Void>> writeComment(@PathVariable Long postId, @RequestParam String comment) {
        commentService.writeComment(postId, comment);
        return BaseResponse.of(null, 200, "success");
    }

    @Operation(summary = "댓글 좋아요")
    @PatchMapping("/{commentId}/like")
    public ResponseEntity<BaseResponse<Void>> likeComment(@PathVariable Long commentId) {
        commentService.likeComment(commentId);
        return BaseResponse.of(null, 200, "success");
    }

    @Operation(summary = "댓글 수정")
    @PatchMapping("/{commentId}")
    public ResponseEntity<BaseResponse<Void>> editComment(@PathVariable Long commentId, @RequestParam String comment) {
        commentService.editComment(commentId, comment);
        return BaseResponse.of(null, 200, "success");
    }

    @Operation(summary = "댓글 삭제")
    @DeleteMapping("/{commentId}")
    public ResponseEntity<BaseResponse<Void>> deleteComment(@PathVariable Long commentId) {
        commentService.deleteComment(commentId);
        return BaseResponse.of(null, 200, "success");
    }
}
