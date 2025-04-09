package com.sparta.yuni.post.ui;

import com.sparta.yuni.common.ui.Response;
import com.sparta.yuni.post.application.interfaces.PostService;
import com.sparta.yuni.post.repository.post_queue.UserPostQueueQueryRepository;
import com.sparta.yuni.user.ui.dto.GetPostContentResponseDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/feed")
@RequiredArgsConstructor
@Slf4j
public class FeedController {

    private final UserPostQueueQueryRepository queueQueryRepository;

    @GetMapping("/{userId}")
    public Response<List<GetPostContentResponseDto>> getPostFeed(@PathVariable(name ="userId") Long userId, Long lastPostId) {

        List<GetPostContentResponseDto> result = queueQueryRepository.getContentResponse(userId, lastPostId);

        return Response.ok(result);
    }

}
