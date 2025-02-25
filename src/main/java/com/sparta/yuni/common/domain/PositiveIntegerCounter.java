package com.sparta.yuni.common.domain;

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

    public int getCount() {
        return count;
    }
}
