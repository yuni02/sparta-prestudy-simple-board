package com.sparta.yuni.user.application.dto;

import com.sparta.yuni.user.domain.User;

public record GetUserResponseDto(Long id, String name, String profileImage,
                                 Integer followingCount, Integer followerCount) {

    public GetUserResponseDto(User user) {
        this(user.getId(), user.getName(), user.getProfileImage(),
            user.followingCounter(), user.followerCounter());
    }

}
