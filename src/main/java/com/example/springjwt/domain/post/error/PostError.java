package com.example.springjwt.domain.post.error;

import com.example.springjwt.global.error.CustomError;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PostError implements CustomError {
    POST_NOT_FOUND(404, "Post not found"),
    EMPTY_TITLE(400, "Empty title"),
    EMPTY_CONTENT(400, "Empty content"),
    CANNOT_EDIT_POST(403, "Cannot edit post"),
    CANNOT_DELETE_POST(403, "Cannot delete post"),
    ;

    private final int status;
    private final String message;

    @Override
    public String getCode() {
        return name();
    }
}