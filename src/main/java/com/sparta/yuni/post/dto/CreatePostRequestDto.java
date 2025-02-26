package com.sparta.yuni.post.dto;

import com.sparta.yuni.post.domain.content.PostPublicationState;

public record CreatePostRequestDto(Long userId, String content, String password, PostPublicationState state) {
}