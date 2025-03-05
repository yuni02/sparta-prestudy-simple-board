package com.sparta.yuni.post.application.interfaces;

import com.sparta.yuni.post.dto.CreateCommentRequestDto;
import com.sparta.yuni.post.dto.LikeRequestDto;
import com.sparta.yuni.post.dto.UpdateCommentRequestDto;
import com.sparta.yuni.post.domain.Post;
import com.sparta.yuni.post.domain.comment.Comment;
import com.sparta.yuni.user.application.UserService;
import com.sparta.yuni.user.domain.User;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    private final UserService userService;
    private final PostService postService;
    private final CommentRepository commentRepository;
    private final LikeRepository likeRepository;

    public CommentService(UserService userService, PostService postService, CommentRepository commentRepository, LikeRepository likeRepository) {
        this.userService = userService;
        this.postService = postService;
        this.commentRepository = commentRepository;
        this.likeRepository = likeRepository;
    }

    public Comment getComment(Long id) {
        return commentRepository.findById(id);
    }

    public Comment createComment(CreateCommentRequestDto dto) {
        Post post = postService.getPost(dto.postId());
        User author = userService.getUser(dto.userId());
        Comment comment = new Comment(null, post, author, dto.content());
        return commentRepository.save(comment);
    }

    public Comment updateComment(Long commentId, UpdateCommentRequestDto dto) {
        Comment comment = getComment(commentId);
        User author = userService.getUser(dto.userId());
        comment.updateContent(author, dto.content());
        return commentRepository.save(comment);
    }

    public void likeComment(LikeRequestDto dto) {
        Comment comment = getComment(dto.targetId());
        User user = userService.getUser(dto.userId());

        if (likeRepository.checkLike(comment, user)) {
            return;
        }

        comment.like(user);
        likeRepository.like(comment, user);
    }

    public void unlikeComment(LikeRequestDto dto) {
        Comment comment = getComment(dto.targetId());
        User user = userService.getUser(dto.userId());

        if (likeRepository.checkLike(comment, user)) {
            comment.unlike();
            likeRepository.unlike(comment, user);
        }
    }
}