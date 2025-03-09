package com.sparta.yuni.post.application.interfaces;

import com.sparta.yuni.post.domain.Post;
import com.sparta.yuni.post.domain.comment.Comment;
import com.sparta.yuni.user.domain.User;

public interface LikeRepository {
    boolean checkLike(Post post, User user);
    boolean checkLike(Comment comment, User user);
    void like(Post post, User user);
    void like(Comment comment, User user);
    void unlike(Post post, User user);
    void unlike(Comment comment, User user);
    void deleteAllByTargetId(Long targetId);

    void deleteAllByComment(Comment comment);
}