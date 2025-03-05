package com.sparta.yuni.post.application.interfaces;

import com.sparta.yuni.post.domain.comment.Comment;

public interface CommentRepository {

    Comment save(Comment comment);

    Comment findById(Long id);


}
