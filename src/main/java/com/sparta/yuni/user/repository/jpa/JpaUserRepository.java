package com.sparta.yuni.user.repository.jpa;

import com.sparta.yuni.user.repository.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaUserRepository extends JpaRepository<UserEntity, Long> {



}
