package org.fastcampus.community_feed.post.repository.entity.post;

import jakarta.persistence.AttributeConverter;
import org.fastcampus.community_feed.post.domain.PostPublicationState;

public class PostPublicationStateConverter implements
    AttributeConverter<PostPublicationState, String> {

    @Override
    public String convertToDatabaseColumn(PostPublicationState postPublicationState) {
        return postPublicationState.name();
    }

    @Override
    public PostPublicationState convertToEntityAttribute(String s) {
        return PostPublicationState.valueOf(s);
    }
}
