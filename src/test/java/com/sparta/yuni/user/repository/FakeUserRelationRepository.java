package com.sparta.yuni.user.repository;

import com.sparta.yuni.user.application.interfaces.UserRelationRepository;
import com.sparta.yuni.user.domain.User;
import java.util.Set;
import java.util.HashSet;

public class FakeUserRelationRepository implements UserRelationRepository {

    private final Set<Relation> store = new HashSet<>();

    @Override
    public boolean isAlreadyFollow(User user, User targetUser) {
        return store.contains(new Relation(user.getId(), targetUser.getId()));
    }

    @Override
    public void save(User user, User targetUser) {
        store.add(new Relation(user.getId(), targetUser.getId()));
    }

    @Override
    public void delete(User use, User targetUser) {
        store.remove(new Relation(targetUser.getId(), use.getId()));

    }
}

record Relation(Long userId, Long targetUserId) {}
