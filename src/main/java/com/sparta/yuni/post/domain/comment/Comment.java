package com.sparta.yuni.post.domain.comment;

import com.sparta.yuni.common.domain.PositiveIntegerCounter;
import com.sparta.yuni.post.domain.Post;
import com.sparta.yuni.post.domain.content.CommentContent;
import com.sparta.yuni.user.domain.User;
import com.sparta.yuni.post.domain.content.Content;
import java.util.Objects;
import lombok.Builder;
import lombok.Getter;


@Getter
public class Comment {

    private final Long id;
    private final Post post;
    private final User author;
    private final Content content;
    private final PositiveIntegerCounter likeCounter;


    @Builder
    public Comment(Long id, Post post, User author, Content content,
        PositiveIntegerCounter likeCounter) {
        if (post == null) {
            throw new IllegalArgumentException("post should not be null");
        }
        if (author == null) {
            throw new IllegalArgumentException("author should not be null");
        }
        if (content == null) {
            throw new IllegalArgumentException("content should not be null or empty");
        }

        this.id = id;
        this.post = post;
        this.author = author;
        this.content = content;
        this.likeCounter = likeCounter;
    }

    public Comment(Long id, Post post, User author, Content content) {
        this(id, post, author, content, new PositiveIntegerCounter());
    }

    public Comment(Long id, Post post, User author, String content) {
        this(id, post, author, new CommentContent(content), new PositiveIntegerCounter());
    }

    public void like(User user) {
        if (this.author.equals(user)) {
            throw new IllegalArgumentException("author cannot like own comment");
        }

        likeCounter.increase();
    }

    public void unlike() {
        this.likeCounter.decrease();
    }

    public int getLikeCount() {
        return likeCounter.getCount();
    }

    public String getContentText() {
        return content.getContentText();
    }

    public void updateContent(User user, String updateContent) {
        if (!this.author.equals(user)) {
            throw new IllegalArgumentException();
        }

        this.content.updateContent(updateContent);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Comment comment = (Comment) o;
        return Objects.equals(id, comment.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
