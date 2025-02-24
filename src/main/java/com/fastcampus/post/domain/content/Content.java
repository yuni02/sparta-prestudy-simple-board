package com.fastcampus.post.domain.content;

import com.fastcampus.post.domain.common.DatetimeInfo;

public abstract class Content{

    String contentText;
    final DatetimeInfo datetimeInfo;

    protected Content(String contentText) {
        checkText(contentText);
        this.datetimeInfo = new DatetimeInfo();
        this.contentText = contentText;

    }

    public void updateContent(String updateContent){
        checkText(updateContent);
        this.contentText = updateContent;
        this.datetimeInfo.updateEditDatetime();
    }

    protected abstract void checkText(String contentText);

    public String getContentText() {
        return contentText;
    }
}
