package com.sparta.yuni.user.application.interfaces;

import com.sparta.yuni.user.domain.User;
import java.util.Optional;

public interface UserRepository {

    User save(User user);

    Optional<User> findById(Long id);
}
