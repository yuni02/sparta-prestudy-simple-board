package com.sparta.yuni.post.ui;

import com.sparta.yuni.common.ui.Response;
import com.sparta.yuni.post.application.interfaces.CommentService;
import com.sparta.yuni.post.domain.comment.Comment;
import com.sparta.yuni.post.dto.CreateCommentRequestDto;
import com.sparta.yuni.post.dto.LikeRequestDto;
import com.sparta.yuni.post.dto.UpdateCommentRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
@Slf4j
public class CommentController {

    private final CommentService commentService;
//    private final CommentQueryRepositoryImpl commentQueryRepository;

    @PostMapping
    public Response<Long> createComment(@RequestBody CreateCommentRequestDto dto) {
        Comment comment = commentService.createComment(dto);
        return Response.ok(comment.getId());
    }

    @PatchMapping("/{commentId}")
    public Response<Long> updateComment(@PathVariable(name = "commentId") Long commentId, @RequestBody UpdateCommentRequestDto dto) {
        Comment comment = commentService.updateComment(commentId, dto);
        return Response.ok(comment.getId());
    }

    @PostMapping("/like")
    public Response<Void> likeComment(@RequestBody LikeRequestDto dto) {
       try {
           commentService.likeComment(dto);
       }catch(Exception e) {
           log.error(e.getMessage());
       }
        return Response.ok(null);
    }

    @PostMapping("/unlike")
    public Response<Void> unlikeComment(@RequestBody LikeRequestDto dto) {
        commentService.unlikeComment(dto);
        return Response.ok(null);
    }

//    @GetMapping("/post/{postId}")
//    public Response<List<GetContentResponseDto>> getCommentList(@PathVariable(name = "postId") Long postId, Long userId, Long lastCommentId) {
//        List<GetContentResponseDto> commentList = commentQueryRepository.getCommentList(postId, userId, lastCommentId);
//        return Response.ok(commentList);
//    }


}
