package com.sparta.yuni.post.repository;

import com.sparta.yuni.post.application.interfaces.PostRepository;
import com.sparta.yuni.post.domain.Post;
import jdk.jfr.Registered;

@Registered
public class PostRepositoryImpl implements PostRepository {

    @Override
    public Post findById(Long id) {
        return null;
    }

    @Override
    public Post save(Post post) {
        return null;
    }

    @Override
    public Post publish(Post post) {
        return null;
    }
}
