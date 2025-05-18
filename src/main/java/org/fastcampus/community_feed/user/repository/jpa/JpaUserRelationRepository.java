package org.fastcampus.community_feed.user.repository.jpa;

import java.util.List;
import org.fastcampus.community_feed.user.repository.entity.UserRelationIdEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface JpaUserRelationRepository extends JpaRepository<org.fastcampus.community_feed.user.repository.entity.UserRelationEntity, UserRelationIdEntity> {


    @Query("SELECT u.followingUserId FROM UserRelationEntity u WHERE u.followerUserId = :userId")
    List<Long> findFollowers(Long userId);

}
