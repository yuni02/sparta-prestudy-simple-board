package com.sparta.yuni.user.application;

import com.sparta.yuni.user.application.interfaces.UserRepository;
import com.sparta.yuni.user.domain.User;
import com.sparta.yuni.user.repository.entity.UserEntity;
import com.sparta.yuni.user.repository.jpa.JpaUserRepository;
import java.util.Optional;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final JpaUserRepository jpaUserRepository;

    @Override
    public User save(User user) {
        UserEntity entity = new UserEntity(user);
        entity = jpaUserRepository.save(entity);
        return entity.toUser();
    }



    @Override
    public User findById(Long id) {
        UserEntity userEntity = jpaUserRepository
            .findById(id)
            .orElseThrow(IllegalArgumentException::new);
        return userEntity.toUser();
    }
}