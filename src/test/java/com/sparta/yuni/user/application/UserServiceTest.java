package com.sparta.yuni.user.application;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.sparta.yuni.user.application.dto.CreateUserRequestDto;
import com.sparta.yuni.user.application.interfaces.UserRepository;
import com.sparta.yuni.user.domain.User;
import com.sparta.yuni.user.domain.UserInfo;
import com.sparta.yuni.user.repository.FakeUserRepository;
import org.junit.jupiter.api.Test;

class UserServiceTest {

    private final UserRepository userRepository = new FakeUserRepository();
    private final UserService userService = new UserService(userRepository);

    @Test
    void givenUserInfoDto_whenCreateUser_thenCanFindUser() {
        //given
        CreateUserRequestDto dto = new CreateUserRequestDto("test", "", "");

        //when
        User savedUser = userService.createUser(dto);

        //then
        User foundUser = userService.getUser(savedUser.getId());
        UserInfo userInfo = foundUser.getInfo();
        assertEquals(foundUser.getId(), savedUser.getId());
        assertEquals("test", userInfo.getName());

    }
}
