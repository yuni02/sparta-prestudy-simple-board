package com.sparta.yuni.post.ui;

import com.sparta.yuni.post.application.interfaces.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

}
