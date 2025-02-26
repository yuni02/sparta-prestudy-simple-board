package com.sparta.yuni.user.ui;

import com.sparta.yuni.common.ui.Response;
import com.sparta.yuni.user.application.UserRelationService;
import com.sparta.yuni.user.application.UserService;
import com.sparta.yuni.user.application.dto.CreateUserRequestDto;
import com.sparta.yuni.user.application.dto.FollowUserRequestDto;
import com.sparta.yuni.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/relation")
@RequiredArgsConstructor
public class UserRelationController {

    private final UserRelationService relationService;

    @PostMapping("/follow")
    public Response<Void> followUser(@RequestBody FollowUserRequestDto dto) {
        relationService.follow(dto);
        return Response.ok(null);
    }

    @PostMapping("/unfollow")
    public Response<Void> unfollowUser(@RequestBody FollowUserRequestDto dto) {
        relationService.unfollow(dto);
        return Response.ok(null);
    }

}
