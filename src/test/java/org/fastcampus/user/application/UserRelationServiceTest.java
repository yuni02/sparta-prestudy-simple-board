package org.fastcampus.user.application;

import com.fastcampus.user.application.UserRelationService;
import com.fastcampus.user.application.UserService;
import com.fastcampus.user.application.interfaces.UserRepository;
import org.fastcampus.user.repository.FakeUserRepository;

class UserRelationServiceTest {
    private final UserRepository userRepository = new FakeUserRepository();
    private final UserService userService = new UserService(userRepository);
    private final UserRelationService userRelationService = new UserRelationService();
}
