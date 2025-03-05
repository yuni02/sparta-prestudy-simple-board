package com.sparta.yuni.post.repository;

import com.sparta.yuni.post.application.interfaces.CommentRepository;
import com.sparta.yuni.post.domain.comment.Comment;
import com.sparta.yuni.post.repository.entity.comment.CommentEntity;
import com.sparta.yuni.post.repository.jpa.JpaCommentRepository;
import com.sparta.yuni.post.repository.jpa.JpaPostRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CommentRepositoryImpl implements CommentRepository {

    private final JpaPostRepository jpaPostRepository;
    private final JpaCommentRepository jpaCommentRepository;

    @Override
    @Transactional
    public Comment save(Comment comment) {
        if (comment.getId() != null) {
            jpaCommentRepository.updateComment(new CommentEntity(comment));
            return comment;
        }
        CommentEntity entity = jpaCommentRepository.save(new CommentEntity(comment));
        return entity.toComment();
    }

    @Override
    public Comment findById(Long id) {
        CommentEntity commentEntity = jpaCommentRepository.findById(id).orElseThrow();
        return commentEntity.toComment();
    }
}
