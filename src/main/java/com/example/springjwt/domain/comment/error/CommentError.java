package com.example.springjwt.domain.comment.error;

import com.example.springjwt.global.error.CustomError;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CommentError implements CustomError {
    COMMENT_NOT_FOUND(404, "Comment not found"),
    EMPTY_CONTENT(400, "Empty content"),
    CANNOT_EDIT_COMMENT(403, "Cannot edit comment"),
    CANNOT_DELETE_COMMENT(403, "Cannot delete comment"),
    ;

    private final int status;
    private final String message;

    @Override
    public String getCode() {
        return name();
    }
}