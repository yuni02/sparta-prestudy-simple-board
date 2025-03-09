package com.sparta.yuni.post.repository.jpa;

import com.sparta.yuni.post.repository.entity.like.LikeEntity;
import com.sparta.yuni.post.repository.entity.like.LikeId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface JpaLikeRepository extends JpaRepository<LikeEntity, LikeId> {
    // 2. @Query 어노테이션을 사용한 방법
    @Modifying
    @Query("DELETE FROM LikeEntity l WHERE l.id.targetId = :targetId AND l.id.targetType = 'POST'")
    void deleteAllByPostTargetId(@Param("targetId") Long postId);

    @Modifying
    @Query("DELETE FROM LikeEntity l WHERE l.id.targetId = :targetId AND l.id.targetType = 'COMMENT'")
    void deleteAllByCommentTargetId(@Param("targetId") Long commentTargetId);
}
