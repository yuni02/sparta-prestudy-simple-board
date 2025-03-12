package com.sparta.yuni.post.repository;

import com.sparta.yuni.post.repository.entity.post.PostEntity;
import com.sparta.yuni.post.repository.entity.post.UserPostQueueEntity;
import com.sparta.yuni.post.repository.jpa.JpaPostRepository;
import com.sparta.yuni.post.repository.jpa.JpaUserPostQueueRepository;
import com.sparta.yuni.post.repository.post_queue.UserPostQueueCommandRepository;
import com.sparta.yuni.user.repository.entity.UserEntity;
import com.sparta.yuni.user.repository.jpa.JpaUserRelationRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserPostQueueCommandRepositoryImpl implements UserPostQueueCommandRepository {

    private final JpaPostRepository jpaPostRepository;
    private final JpaUserRelationRepository JpaUserRelationRepository;
    private final JpaUserPostQueueRepository JpaUserPostQueueRepository;
    private final JpaUserRelationRepository jpaUserRelationRepository;
    private final JpaUserPostQueueRepository jpaUserPostQueueRepository;


    @Override
    @Transactional
    public void publishPost(PostEntity postEntity) {
        UserEntity userEntity = postEntity.getAuthor();
        List<Long> followersIds = jpaUserRelationRepository.findFollowers(userEntity.getId());
        List<UserPostQueueEntity> userPostQueueEntityList = followersIds.stream()
            .map(userId -> new UserPostQueueEntity(userId, postEntity.getId(), userEntity.getId()))
            .toList();

        jpaUserPostQueueRepository.saveAll(userPostQueueEntityList);
    }

    @Override
    @Transactional
    public void saveFollowPost(Long userId, Long targetId) {
        List<Long> postIdList = jpaPostRepository.findAllPostIdsByAuthorId(targetId);
        List<UserPostQueueEntity> userPostQueueEntityList = postIdList.stream()
            .map(postId -> new UserPostQueueEntity(userId, postId, targetId)).toList();

        System.out.println("list>>"+userPostQueueEntityList);

        jpaUserPostQueueRepository.saveAll(userPostQueueEntityList);
    }

    @Override
    public void deleteUnfollowPost(Long userId, Long targetId) {
        jpaUserPostQueueRepository.deleteAllByUserIdAndAuthorId(userId, targetId);
    }
}
