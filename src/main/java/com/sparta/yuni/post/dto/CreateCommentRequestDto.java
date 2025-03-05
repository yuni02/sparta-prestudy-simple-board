package com.sparta.yuni.post.dto;

public record CreateCommentRequestDto(
    Long postId,
    Long userId,
    String content
) {

}