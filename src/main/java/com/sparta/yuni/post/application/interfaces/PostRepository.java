package com.sparta.yuni.post.application.interfaces;

import com.sparta.yuni.post.domain.Post;
import java.util.List;

public interface PostRepository {

    Post findById(Long id);
    Post save(Post post);

    List<Post> findByAuthorId(Long authorId);
    void deleteById(Long id);
}
