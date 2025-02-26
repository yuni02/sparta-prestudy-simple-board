package com.sparta.yuni.post.repository.jpa;

import com.sparta.yuni.post.repository.entity.post.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface JpaPostRepository extends JpaRepository<PostEntity, Long> {

}
