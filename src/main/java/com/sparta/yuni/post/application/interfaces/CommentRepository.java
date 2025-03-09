package com.sparta.yuni.post.application.interfaces;

import com.sparta.yuni.post.domain.Post;
import com.sparta.yuni.post.domain.comment.Comment;
import java.util.List;

public interface CommentRepository {

    Comment save(Comment comment);

    Comment findById(Long id);

    void deleteAllByPostId(Long postId);

    List<Comment> deleteAllByPostIdAndReturn(Long postId);
}
