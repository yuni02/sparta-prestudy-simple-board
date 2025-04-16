package com.sparta.yuni.acceptance.utils;

import static com.sparta.yuni.acceptance.post.UserAcceptanceSteps.createUser;
import static com.sparta.yuni.acceptance.post.UserAcceptanceSteps.followUser;

import com.sparta.yuni.user.application.dto.CreateUserRequestDto;
import com.sparta.yuni.user.application.dto.FollowUserRequestDto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Component;

@Component
public class DataLoader {

    @PersistenceContext
    private EntityManager entityManager;

    public void loadData() {
        // user 1, 2, 3 생성 및 user 1 follow user 2, user 3
        CreateUserRequestDto user = new CreateUserRequestDto("user", null);
        createUser(user);
        createUser(user);
        createUser(user);

        followUser(new FollowUserRequestDto(1L, 2L));
        followUser(new FollowUserRequestDto(2L, 3L));
    }

    public String getEmailToken(String email) {
        return entityManager.createNativeQuery(
                "SELECT token FROM community_email_verification WHERE email =? ", String.class)
            .setParameter(1, email).getSingleResult().toString();
    }
}