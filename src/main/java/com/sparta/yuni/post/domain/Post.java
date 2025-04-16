package com.sparta.yuni.post.domain;

import com.sparta.yuni.common.domain.PositiveIntegerCounter;
import com.sparta.yuni.post.domain.content.Content;
import com.sparta.yuni.post.domain.content.PostPublicationState;
import com.sparta.yuni.user.domain.User;
import com.sparta.yuni.post.domain.content.PostContent;
import lombok.Builder;
import lombok.Getter;


import java.util.Objects;

@Builder
@Getter
public class Post {

    private final Long id;
    private final User author;
    private final Content content;
    private PostPublicationState state;
    private final PositiveIntegerCounter likeCount;

    @Builder
    public Post(Long id, User author, Content content, PostPublicationState state,
        PositiveIntegerCounter positiveIntegerCounter) {
        if (author == null) {
            throw new IllegalArgumentException("author should not be null");
        }
        if (content == null) {
            throw new IllegalArgumentException("content should not be null or empty");
        }

        this.id = id;

        this.author = author;
        this.content = content;
        this.state = state;
        this.likeCount = positiveIntegerCounter;
    }

    public Post(Long id, User author, Content content) {
        this(id, author, content, PostPublicationState.PUBLIC,
            new PositiveIntegerCounter());
    }

    public Post(Long id, User author, String content) {
        this(id, author, new PostContent(content), PostPublicationState.PUBLIC,
            new PositiveIntegerCounter());
    }

    public void updateContent(User user, String content, PostPublicationState state, String title) {
        if (!author.equals(user)) {
            throw new IllegalArgumentException("only author can update content");
        }

        if (state == null) {
            state = PostPublicationState.PUBLIC;
        }

        this.content.updateContent(content);
        this.state = state;
    }

    public void like(User user) {
        if (author.equals(user)) {
            throw new IllegalArgumentException("author cannot like own post");
        }
        likeCount.increase();
    }

    public void unlike() {
        likeCount.decrease();
    }

    public int getLikeCount() {
        return likeCount.getCount();
    }

    public String getContent() {
        return content.getContentText();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Post post = (Post) o;
        return Objects.equals(id, post.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}