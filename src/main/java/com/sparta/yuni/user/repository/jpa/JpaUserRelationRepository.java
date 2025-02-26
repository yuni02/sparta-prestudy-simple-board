package com.sparta.yuni.user.repository.jpa;

import com.sparta.yuni.user.repository.entity.UserRelationEntity;
import com.sparta.yuni.user.repository.entity.UserRelationIdEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaUserRelationRepository extends JpaRepository<UserRelationEntity, UserRelationIdEntity> {


}
