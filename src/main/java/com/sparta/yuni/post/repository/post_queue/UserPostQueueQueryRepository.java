package com.sparta.yuni.post.repository.post_queue;

import com.sparta.yuni.user.ui.dto.GetPostContentResponseDto;
import java.util.List;

public interface UserPostQueueQueryRepository {

    List<GetPostContentResponseDto> getPostList(Long userId, Long lastPostId);

    List<GetPostContentResponseDto> getContentResponse(Long userId, Long lastPostId);
}
