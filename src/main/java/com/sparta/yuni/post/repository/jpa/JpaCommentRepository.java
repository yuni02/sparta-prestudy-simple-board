package com.sparta.yuni.post.repository.jpa;

import com.sparta.yuni.post.repository.entity.comment.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import com.sparta.yuni.post.domain.comment.Comment;

public interface JpaCommentRepository extends JpaRepository<CommentEntity, Long> {

    @Modifying
    @Query(value = "UPDATE CommentEntity c SET c.likeCount= :#{#comment.getLikeCount()}, c.updDt = now() WHERE c.id = :#{#comment.getId()}")
    void updateLikeCount(Comment comment);

    @Modifying
    @Query(value = "UPDATE CommentEntity c SET c.content = :#{#comment.getContent()}, c.updDt = now() WHERE c.id = :#{#comment.getId()}")
    void updateComment(CommentEntity comment);



}
