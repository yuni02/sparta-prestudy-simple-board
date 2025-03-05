package com.sparta.yuni.post.application.interfaces;

import com.sparta.yuni.post.domain.Post;

public interface PostRepository {

    Post findById(Long id);
    Post save(Post post);



}
