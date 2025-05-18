package org.fastcampus.community_feed.user.repository.jpa;


import static org.fastcampus.community_feed.user.repository.entity.QUserEntity.userEntity;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.fastcampus.community_feed.user.application.dto.GetUserListResponseDto;
import org.fastcampus.community_feed.user.repository.entity.QUserEntity;
import org.fastcampus.community_feed.user.repository.entity.QUserRelationEntity;
import org.springframework.stereotype.Repository;
import com.querydsl.core.types.Projections;

@Repository
@RequiredArgsConstructor
public class JpaUserListPagingQueryRepository {

    private final JPAQueryFactory jpaQueryFactory;
    private final QUserEntity user = userEntity;
    private final QUserRelationEntity relation = QUserRelationEntity.userRelationEntity;

    public List<GetUserListResponseDto> getFollowerList(Long userId, Long lastFollowerId) {
        return jpaQueryFactory.select(Projections.fields(
                    GetUserListResponseDto.class
                )
            )
            .from(relation)
            .join(user)
            .on(relation.followerUserId.eq(user.id), hasLastData(lastFollowerId))
            .where(relation.followerUserId.eq(userId))
            .orderBy(user.id.desc())
            .limit(20)
            .fetch();
    }

    private BooleanExpression hasLastData(Long lastId) {
        if (lastId == null) {
            return null;
        }

        return userEntity.id.lt(lastId);

    }

}
