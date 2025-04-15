package com.sparta.yuni.acceptance.post;

import static com.sparta.yuni.acceptance.post.FeedAcceptanceSteps.requestCreatePost;
import static com.sparta.yuni.acceptance.post.FeedAcceptanceSteps.requestFeedList;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import com.sparta.yuni.acceptance.utils.AcceptanceTestTemplate;
import com.sparta.yuni.post.dto.CreatePostRequestDto;
import com.sparta.yuni.post.dto.GetPostContentResponseDto;
import com.sparta.yuni.post.domain.content.PostPublicationState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class FeedAcceptanceTest extends AcceptanceTestTemplate {

    /**
     * User 1 --- follow ---> User 2
     * User 1 --- follow ---> User 3
     */
    @BeforeEach
    void init() {
        super.setUp();
    }

    /**
     * User 2 create Post 1
     * User 1 Get Post 1 From Feed
     */
    @Test
    void givenUserHasFollowerWhenCreatePostThenFollowerFeedCanGetPost() {
        // given
        CreatePostRequestDto dto = new CreatePostRequestDto(2L, "1 content", PostPublicationState.PUBLIC);
        Long createdPostId = requestCreatePost(dto);

        // when, 팔로워의 피드 요청
        List<GetPostContentResponseDto> result = requestFeedList(1L);

        // then
        assertEquals(1, result.size());
        assertEquals(createdPostId, result.get(0).getId());
    }
}
