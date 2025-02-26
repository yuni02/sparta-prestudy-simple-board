package com.sparta.yuni.post.repository;

import com.sparta.yuni.post.application.interfaces.LikeRepository;
import com.sparta.yuni.post.domain.Post;
import com.sparta.yuni.post.domain.comment.Comment;
import com.sparta.yuni.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class LikeRepositoryImpl implements LikeRepository {

    @Override
    public boolean checkLike(Post post, User user) {
        return false;
    }

    @Override
    public boolean checkLike(Comment comment, User user) {
        return false;
    }

    @Override
    public void like(Post post, User user) {

    }

    @Override
    public void like(Comment comment, User user) {

    }

    @Override
    public void unlike(Post post, User user) {

    }

    @Override
    public void unlike(Comment comment, User user) {

    }
}
