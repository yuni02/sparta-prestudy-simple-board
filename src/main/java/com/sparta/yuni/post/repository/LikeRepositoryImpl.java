package com.sparta.yuni.post.repository;

import com.sparta.yuni.post.application.interfaces.LikeRepository;
import com.sparta.yuni.post.domain.Post;
import com.sparta.yuni.post.domain.comment.Comment;
import com.sparta.yuni.post.repository.entity.comment.CommentEntity;
import com.sparta.yuni.post.repository.entity.like.LikeEntity;
import com.sparta.yuni.post.repository.entity.post.PostEntity;
import com.sparta.yuni.post.repository.jpa.JpaCommentRepository;
import com.sparta.yuni.post.repository.jpa.JpaLikeRepository;
import com.sparta.yuni.post.repository.jpa.JpaPostRepository;
import com.sparta.yuni.user.domain.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class LikeRepositoryImpl implements LikeRepository {

    @PersistenceContext
    private final EntityManager entityManager;

    private final JpaPostRepository jpaPostRepository;
    private final JpaCommentRepository jpaCommentRepository;
    private final JpaLikeRepository jpaLikeRepository;

    @Override
    public boolean checkLike(Post post, User user) {
        LikeEntity likeEntity = new LikeEntity(post, user);

        return jpaLikeRepository.existsById(likeEntity.getId());
    }

    @Override
    public boolean checkLike(Comment comment, User user) {
        LikeEntity likeEntity = new LikeEntity(comment, user);
        return jpaLikeRepository.existsById(likeEntity.getId());
    }

    @Override
    public void like(Post post, User user) {
        LikeEntity likeEntity = new LikeEntity(post, user);
        entityManager.persist(likeEntity); //불필요하게 저장안되게 함. 동일한 id를 넣어도 pk 제약 때문에 중복 데이터 삽입 방지됨.
        jpaPostRepository.updateLikeCount(new PostEntity(post));
    }

    @Override
    public void like(Comment comment, User user) {
        LikeEntity likeEntity = new LikeEntity(comment, user);
        jpaLikeRepository.save(likeEntity);
        jpaCommentRepository.updateLikeCount(comment);

    }

    @Override
    public void unlike(Post post, User user) {

        LikeEntity likeEntity = new LikeEntity(post, user);
        jpaLikeRepository.deleteById(likeEntity.getId());
        jpaPostRepository.updateLikeCount(new PostEntity(post));
    }

    @Override
    public void unlike(Comment comment, User user) {
        LikeEntity likeEntity = new LikeEntity(comment, user);
        jpaLikeRepository.deleteById(likeEntity.getId());
        jpaCommentRepository.updateLikeCount(comment);

    }
}
