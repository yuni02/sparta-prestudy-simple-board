package com.sparta.yuni.post.dto;

import com.sparta.yuni.post.domain.content.PostPublicationState;

public record UpdatePostRequestDto (
    Long userId,
    String content,
    PostPublicationState state
) { }