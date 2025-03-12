package com.sparta.yuni.post.ui;

import com.sparta.yuni.common.domain.exception.ErrorCode;
import com.sparta.yuni.common.ui.Response;
import com.sparta.yuni.post.application.interfaces.PostService;
import com.sparta.yuni.post.domain.Post;
import com.sparta.yuni.post.dto.CreatePostRequestDto;
import com.sparta.yuni.post.dto.LikeRequestDto;
import com.sparta.yuni.post.application.dto.PostResponseDto;
import com.sparta.yuni.post.dto.UpdatePostRequestDto;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
@Slf4j
public class PostController {

    private final PostService postService;

    @GetMapping
    public Response<List<PostResponseDto>> getAllPosts(@RequestParam(required = true) Long authorId) {
        List<Post> posts = postService.getAllPosts(authorId);
        List<PostResponseDto> responseList = posts.stream()
            .map(PostResponseDto::from)
            .collect(Collectors.toList());
        return Response.ok(responseList);
    }

    @PostMapping
    public Response<Long> createPost(@RequestBody CreatePostRequestDto dto) {
        Post post = postService.createPost(dto);
        return Response.ok(post.getId());
    }

    @PatchMapping("/{postId}")
    public Response<Long> updatePost(@PathVariable(name = "postId") Long postId,
        @RequestBody UpdatePostRequestDto dto) {
        Post post = postService.updatePost(postId, dto);
        return Response.ok(post.getId());
    }

    @DeleteMapping("/{postId}")
    public Response<Long> deletePost(@PathVariable(name = "postId") Long postId) {
        try {
            postService.deletePost(postId);
            return Response.ok(postId);
        } catch (Exception e) {
            log.error(e.getMessage());
            // 오류 처리 - 적절한 응답 반환
            return Response.error(ErrorCode.INTERNAL_ERROR); // 또는 다른 적절한 에러코드
        }
    }


    @PostMapping("/like")
    public Response<Void> likePost(@RequestBody LikeRequestDto dto) {
        postService.likePost(dto);
        return Response.ok(null);
    }

    @PostMapping("/unlike")
    public Response<Void> unlikePost(@RequestBody LikeRequestDto dto) {
        postService.unlikePost(dto);
        return Response.ok(null);
    }


}
