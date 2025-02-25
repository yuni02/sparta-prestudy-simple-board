package com.sparta.yuni.user.application;

import com.sparta.yuni.user.application.interfaces.UserRepository;
import com.sparta.yuni.user.repository.FakeUserRepository;

class UserRelationServiceTest {
    private final UserRepository userRepository = new FakeUserRepository();
    private final UserService userService = new UserService(userRepository);
//    private final UserRelationService userRelationService = new UserRelationService();
}
