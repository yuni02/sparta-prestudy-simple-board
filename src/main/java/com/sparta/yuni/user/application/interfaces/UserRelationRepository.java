package com.sparta.yuni.user.application.interfaces;

import com.sparta.yuni.user.domain.User;

public interface UserRelationRepository {
    // 굳이 전체 도메인을 다 넘기는 이유는 User 내부만 변경해도 인테페이스는 변경할 필요가 없기 때문.
    boolean isAlreadyFollow(User user, User targetUser);
    void save(User user, User targetUser);
    void delete(User use, User targetUser);


}
