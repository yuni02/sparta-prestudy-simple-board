package com.sparta.yuni.post.dto;

import com.sparta.yuni.post.domain.content.PostPublicationState;

public record UpdatePostRequestDto (
    Long userId,
    String content,
    String title,
    String password,
    PostPublicationState state
) { }