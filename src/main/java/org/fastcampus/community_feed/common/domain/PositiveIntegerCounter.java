package org.fastcampus.community_feed.common.domain;

import lombok.Getter;

@Getter
public class PositiveIntegerCounter {

    private int count;

    public PositiveIntegerCounter(int likeCount) {
        this.count = likeCount;
    }

    public PositiveIntegerCounter() {
        this(0);
    }

    public void increase() {
        this.count++;
    }

    public void decrease() {
        if (this.count <= 0) {
            return;
        }
        this.count--;
    }

}
