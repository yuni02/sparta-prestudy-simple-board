package com.fastcampus.post.domain.comment;

import com.fastcampus.common.domain.PositiveIntegerCounter;
import com.fastcampus.post.domain.Post;
import com.fastcampus.user.domain.User;
import com.fastcampus.post.domain.content.Content;

public class Comment {

    private final Long id;
    private final Post post;
    private final User author;
    private final Content content;
    private final PositiveIntegerCounter likeCount;

    public Comment(Long id, Post post, User author, Content content) {

        if (author == null) {
            throw new IllegalArgumentException();
        }

        if (content == null) {
            throw new IllegalArgumentException();
        }

        if (post == null) {
            throw new IllegalArgumentException();
        }

        this.id = id;
        this.post = post;
        this.author = author;
        this.content = content;
        this.likeCount = new PositiveIntegerCounter();

    }

    public void like(User user){
        if(!this.author.equals(user)){
            throw new IllegalArgumentException();
        }

        likeCount.increase();
    }

    public void unlike(){
        this.likeCount.decrease();
    }

    public void updateContent(User user, String updateContent){
        if(!this.author.equals(user)){
            throw new IllegalArgumentException();
        }

        this.content.updateContent(updateContent);
    }
}
