package com.sparta.yuni.post.application.interfaces;

import com.sparta.yuni.post.domain.Post;
import com.sparta.yuni.post.dto.CreatePostRequestDto;
import com.sparta.yuni.post.dto.LikeRequestDto;
import com.sparta.yuni.post.dto.UpdatePostRequestDto;
import com.sparta.yuni.user.application.UserService;
import com.sparta.yuni.user.domain.User;
import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class PostService {

    private final UserService userService;
    private final PostRepository postRepository;
    private final LikeRepository likeRepository;
    private final CommentRepository commentRepository;


    public PostService(UserService userService, PostRepository postRepository,
        LikeRepository likeRepository, CommentRepository commentRepository) {
        this.userService = userService;
        this.postRepository = postRepository;
        this.likeRepository = likeRepository;
        this.commentRepository = commentRepository;
    }

    public List<Post> getAllPost(Long authorId){
        return postRepository.findByAuthorId(authorId);
    }

    public Post getPost(Long id) {
        return postRepository.findById(id);
    }

    public Post createPost(CreatePostRequestDto dto) {
        User author = userService.getUser(dto.userId());
        Post post = new Post(null, author, dto.content(), dto.password());
        return postRepository.save(post);
    }

    public Post updatePost(Long postId, UpdatePostRequestDto dto) {
        Post post = getPost(postId);
        User user = userService.getUser(dto.userId());

        post.updateContent(user, dto.content(), dto.state(), dto.password(), dto.title());
        return postRepository.save(post);
    }

    @Transactional
    public void likePost(LikeRequestDto dto) {
        Post post = getPost(dto.targetId());
        User user = userService.getUser(dto.userId());

        if (likeRepository.checkLike(post, user)) {
            return;
        }

        post.like(user);
        likeRepository.like(post, user);
    }

    public void unlikePost(LikeRequestDto dto) {
        Post post = getPost(dto.targetId());
        User user = userService.getUser(dto.userId());

        if (likeRepository.checkLike(post, user)) {
            post.unlike();
            likeRepository.unlike(post, user);
        }
    }

    @Transactional
    public void deletePost(Long postId) {
        postRepository.deleteById(postId);
    }

    public List<Post> getAllPosts(Long authorId) {
        return postRepository.findByAuthorId(authorId);
    }


}