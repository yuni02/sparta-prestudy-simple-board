package com.sparta.yuni.post.repository;

import com.sparta.yuni.post.application.interfaces.PostRepository;
import com.sparta.yuni.post.domain.Post;
import com.sparta.yuni.post.repository.entity.post.PostEntity;
import com.sparta.yuni.post.repository.jpa.JpaPostRepository;
import com.sparta.yuni.post.repository.post_queue.UserPostQueueCommandRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PostRepositoryImpl implements PostRepository {

    private final JpaPostRepository jpaPostRepository;
    private final UserPostQueueCommandRepository commandRepository;

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
        commandRepository.publishPost(postEntity);
        return postEntity.toPost();
    }

    @Override
    public List<Post> findByAuthorId(Long authorId) {
        List<PostEntity> postEntities = jpaPostRepository.findAllByAuthorId(authorId);
        return postEntities.stream()
            .map(PostEntity::toPost)
            .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        jpaPostRepository.deleteById(id);
    }
}
