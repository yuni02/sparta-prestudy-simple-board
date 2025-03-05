package com.sparta.yuni.post.dto;

public record UpdateCommentRequestDto(
    Long userId,
    String content
) {

}