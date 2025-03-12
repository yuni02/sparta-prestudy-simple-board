package com.sparta.yuni.post.application.dto;

import com.sparta.yuni.post.domain.Post;
import com.sparta.yuni.post.domain.content.PostPublicationState;

public record PostResponseDto(
    Long id,
    String content,
    String authorName,
    String authorId,
    PostPublicationState state,
    int likeCount
) {
    public static PostResponseDto from(Post post) {
        return new PostResponseDto(
            post.getId(),
            post.getContent(),
            post.getAuthor().getName(),
            post.getAuthor().getId().toString(),
            post.getState(),
            post.getLikeCount()
        );
    }
}