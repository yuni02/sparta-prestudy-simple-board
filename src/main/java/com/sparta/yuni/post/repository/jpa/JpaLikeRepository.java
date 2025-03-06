package com.sparta.yuni.post.repository.jpa;

import com.sparta.yuni.post.repository.entity.like.LikeEntity;
import com.sparta.yuni.post.repository.entity.like.LikeId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaLikeRepository extends JpaRepository<LikeEntity, LikeId> {

}
