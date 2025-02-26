package com.sparta.yuni.post.repository;

import com.sparta.yuni.post.application.interfaces.PostRepository;
import com.sparta.yuni.post.domain.Post;
import com.sparta.yuni.post.repository.entity.post.PostEntity;
import com.sparta.yuni.post.repository.jpa.JpaPostRepository;
import jdk.jfr.Registered;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PostRepositoryImpl implements PostRepository {


    private final JpaPostRepository jpaPostRepository;

    @Override
    public Post findById(Long id) {
        PostEntity postEntity = jpaPostRepository.findById(id).orElseThrow();
        return postEntity.toPost();
    }

    @Override
    public Post save(Post post) {
        PostEntity postEntity = new PostEntity();
        postEntity = jpaPostRepository.save(postEntity);
        return postEntity.toPost();
    }

    @Override
    public Post publish(Post post) {
        return null;
    }


}
