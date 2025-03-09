package com.sparta.yuni.post.repository;

import com.sparta.yuni.post.application.interfaces.CommentRepository;
import com.sparta.yuni.post.domain.Post;
import com.sparta.yuni.post.domain.comment.Comment;
import com.sparta.yuni.post.repository.entity.comment.CommentEntity;
import com.sparta.yuni.post.repository.jpa.JpaCommentRepository;
import com.sparta.yuni.post.repository.jpa.JpaPostRepository;
import jakarta.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
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
        Post targetPost = comment.getPost();
        if (comment.getId() != null) {
            jpaCommentRepository.updateComment(new CommentEntity(comment));
            return comment;
        }
        CommentEntity entity = jpaCommentRepository.save(new CommentEntity(comment));
        jpaPostRepository.increaseCommentCount(targetPost.getId());
        return entity.toComment();
    }

    @Override
    public Comment findById(Long id) {
        CommentEntity commentEntity = jpaCommentRepository.findById(id).orElseThrow();
        return commentEntity.toComment();
    }

    @Transactional
    public List<Comment> deleteAllByPostIdAndReturn(Long postId) {
        // 1. 먼저 해당 게시물의 모든 댓글 조회
        List<Comment> comments = jpaCommentRepository.findAllEntityByPostId(postId).stream()
            .map(CommentEntity::toComment)
            .collect(Collectors.toList());

        // 2. 댓글 삭제
        deleteAllByPostId(postId);

        // 3. 삭제된 댓글 목록 반환
        return comments;
    }

    @Override
    public void deleteAllByPostId(Long postId) {
        jpaCommentRepository.deleteAllByPostId(postId);

    }

}
