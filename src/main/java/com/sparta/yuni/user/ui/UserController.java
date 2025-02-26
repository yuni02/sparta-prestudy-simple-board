package com.sparta.yuni.user.ui;

import com.sparta.yuni.common.ui.Response;
import com.sparta.yuni.user.application.UserService;
import com.sparta.yuni.user.application.dto.CreateUserRequestDto;
import com.sparta.yuni.user.application.dto.GetUserListResponseDto;
import com.sparta.yuni.user.application.dto.GetUserResponseDto;
import com.sparta.yuni.user.domain.User;
import com.sparta.yuni.user.repository.jpa.JpaUserListQueryRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    private final JpaUserListQueryRepository userListQueryRepository;

    @PostMapping
    public Response<Long> createUser(@RequestBody CreateUserRequestDto dto) {
        User user = userService.createUser(dto);
        return Response.ok(user.getId());
    }

    @GetMapping("/{userId}")
    public Response<GetUserResponseDto> getUserProfile(@PathVariable(name="userId") Long userId) {
        return Response.ok(userService.getUserProfile(userId));
    }

    @GetMapping("/{userId}/following")
    public Response<List<GetUserListResponseDto>> getFollowingUserList(@PathVariable(name = "userId") Long userId) {
        List<GetUserListResponseDto> result = userListQueryRepository.getFollowingUserList(userId);
        return Response.ok(result);
    }

    @GetMapping("/{userId}/follower")
    public Response<List<GetUserListResponseDto>> getFollowerList(@PathVariable(name = "userId") Long userId) {
        List<GetUserListResponseDto> result = userListQueryRepository.getFollowerUserList(userId);
        return Response.ok(result);
    }

}
