package com.sparta.yuni.post.repository;

import com.sparta.yuni.post.application.interfaces.PostRepository;
import com.sparta.yuni.post.domain.Post;
import com.sparta.yuni.post.repository.entity.post.PostEntity;
import com.sparta.yuni.post.repository.jpa.JpaPostRepository;
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
        PostEntity postEntity = new PostEntity(post);
        if(post.getId()!=null){
            jpaPostRepository.updatePostEntity(postEntity);
            return postEntity.toPost();
        }
        postEntity = jpaPostRepository.save(postEntity);
        return postEntity.toPost();
    }
}
