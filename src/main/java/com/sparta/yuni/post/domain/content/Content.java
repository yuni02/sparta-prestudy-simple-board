package com.sparta.yuni.post.domain.content;

import com.sparta.yuni.post.domain.common.DatetimeInfo;
import lombok.Getter;

@Getter
public abstract class Content {
    String contentText;
    final DatetimeInfo datetimeInfo;

    Content(String content) {
        checkLength(content);
        this.contentText = content;
        this.datetimeInfo = new DatetimeInfo();
    }

    public void updateContent(String content) {
        checkLength(content);
        this.contentText = content;
        this.datetimeInfo.updateEditDatetime();
    }

    abstract void checkLength(String content);

    public boolean isEdited() {
        return datetimeInfo.isEdited();
    }
}
